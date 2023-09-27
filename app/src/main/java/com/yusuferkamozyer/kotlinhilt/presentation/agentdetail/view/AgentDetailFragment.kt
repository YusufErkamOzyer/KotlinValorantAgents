package com.yusuferkamozyer.kotlinhilt.presentation.agentdetail.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusuferkamozyer.kotlinhilt.MainActivity
import com.yusuferkamozyer.kotlinhilt.R
import com.yusuferkamozyer.kotlinhilt.data.local.model.AgentDatabase
import com.yusuferkamozyer.kotlinhilt.databinding.FragmentAgentDetailBinding
import com.yusuferkamozyer.kotlinhilt.presentation.agentdetail.AgentDetailViewModel
import com.yusuferkamozyer.kotlinhilt.presentation.agentdetail.adapter.AgentDetailAdapter
import com.yusuferkamozyer.kotlinhilt.presentation.model.ViewItem
import com.yusuferkamozyer.kotlinhilt.util.downloadImage
import com.yusuferkamozyer.kotlinhilt.util.placeHolderProgressBar
import com.yusuferkamozyer.kotlinvalorant.domain.model.AgentDetail
import com.yusuferkamozyer.kotlinvalorant.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AgentDetailFragment @Inject constructor(): Fragment() {
    private var _binding: FragmentAgentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var agentDetail: AgentDetail
    private val viewModel:AgentDetailViewModel by viewModels()
    private val args:AgentDetailFragmentArgs by navArgs()
    private lateinit var  agentDetailAdapter:AgentDetailAdapter
    private var activity: MainActivity?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity=activity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAgentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    private var isContained:Boolean=false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val agentuuid=args.agentID
        viewModel.getAgentDetail(agentuuid)
        binding.addMyFav.setOnClickListener {
            addFavoriteOrDelete()
        }
        observeLiveData()
    }
    private fun observeLiveData(){
        viewModel.state.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success->{
                    it.data?.let {agentDetail1 ->
                        agentDetail=agentDetail1
                        loadData(agentDetail)
                        isContained=viewModel.isContain(agentDetail)
                        if (isContained){
                            binding.addMyFav.setBackgroundResource(R.drawable.baseline_favorite_24)
                        }
                    }

                    binding.progressBarDetail.visibility=View.GONE
                }
                is Resource.Loading->{
                    binding.progressBarDetail.visibility=View.VISIBLE
                    binding.errorView.visibility=View.GONE
                }
                is Resource.Error->{
                    binding.errorView.text=it.message
                    binding.errorView.visibility=View.VISIBLE
                    binding.progressBarDetail.visibility=View.GONE
                }

            }
        })
    }
    private fun loadData(agentDetail: AgentDetail){
        binding.imageBackground.downloadImage(agentDetail.fullPortrait, placeHolderProgressBar(requireContext()))
        binding.agentName.text=agentDetail.displayName
        binding.agentDesc.text=agentDetail.description
        val randomIndexes=generateRondomList()
        binding.constraintLayout.setBackgroundColor(Color.parseColor("#"+agentDetail.backgroundGradientColors.get(randomIndexes[0])))

        binding.imageRole.downloadImage(agentDetail.role.displayIcon,placeHolderProgressBar(requireContext()))
        binding.roleName.text=agentDetail.role.displayName
        binding.roleDesc.text=agentDetail.role.description
        val abilityList= arrayListOf<ViewItem>()
        for(i in agentDetail.abilities){
            val ability= ViewItem(i.displayIcon,i.displayName,i.description)
            abilityList.add(ability)
        }
        agentDetailAdapter=AgentDetailAdapter(abilityList)
        binding.detailRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        binding.detailRecyclerView.adapter=agentDetailAdapter


    }

    private fun addFavoriteOrDelete(){
        val currentAgentDatabase=AgentDatabase(agentDetail.displayName,agentDetail.uuid,agentDetail.displayIcon)
        if (isContained){
            viewModel.removeFav(currentAgentDatabase)
            binding.addMyFav.setBackgroundResource(R.drawable.baseline_favorite_border_24)
            isContained=false
        }
        else{
            viewModel.addFavs(currentAgentDatabase)
            binding.addMyFav.setBackgroundResource(R.drawable.baseline_favorite_24)
            isContained=true
        }
    }
    private fun generateRondomList():ArrayList<Int>{
        val arrayList= arrayListOf<Int>()
        while (true){
            if (arrayList.size==4){
                break
            }
            var value= kotlin.random.Random.nextInt(0,4)
            if (!(value in arrayList)){
                arrayList.add(value)
            }
        }
        return arrayList
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null

    }





}