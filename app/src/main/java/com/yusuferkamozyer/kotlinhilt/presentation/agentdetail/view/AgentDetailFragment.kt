package com.yusuferkamozyer.kotlinhilt.presentation.agentdetail.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.yusuferkamozyer.kotlinhilt.R
import com.yusuferkamozyer.kotlinhilt.data.local.model.AgentDatabase
import com.yusuferkamozyer.kotlinhilt.databinding.FragmentAgentDetailBinding
import com.yusuferkamozyer.kotlinhilt.presentation.agentdetail.AgentDetailViewModel
import com.yusuferkamozyer.kotlinhilt.presentation.agentdetail.ViewItem
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAgentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    val viewModel:AgentDetailViewModel by viewModels()
    val args:AgentDetailFragmentArgs by navArgs()
    private var isContained:Boolean=false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val agentuuid=args.agentID
        viewModel.getAgentDetail(agentuuid)
        try {
            binding.addMyFav.setOnClickListener {
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

        }catch (e:Exception){
            println(e.localizedMessage)
        }






        observeLiveData()
    }
    fun observeLiveData(){
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
                else -> {
                    println("else state")
                }
            }
        })
    }
    fun loadData(agentDetail: AgentDetail){
        binding.imageBackground.downloadImage(agentDetail.fullPortrait, placeHolderProgressBar(requireContext()))
        binding.agentName.text=agentDetail.displayName
        binding.agentDesc.text=agentDetail.description
        val randomIndexes=generateRondomList()
        binding.constraintLayout.setBackgroundColor(Color.parseColor("#"+agentDetail.backgroundGradientColors.get(randomIndexes[0])))

        binding.imageRole.downloadImage(agentDetail.role.displayIcon,placeHolderProgressBar(requireContext()))
        binding.roleName.text=agentDetail.role.displayName
        binding.roleDesc.text=agentDetail.role.description

        val firstViewItem=ViewItem(binding.ability1View,binding.ability1Name,binding.ability1Desc)
        val secondViewItem=ViewItem(binding.ability2View,binding.ability2Name,binding.ability2Desc)
        val thirdViewItem=ViewItem(binding.ability3View,binding.ability3Name,binding.ability3Desc)
        val fourthViewItem=ViewItem(binding.ability4View,binding.ability4Name,binding.ability4Desc)
        val itemViewList= arrayListOf(firstViewItem,secondViewItem,thirdViewItem,fourthViewItem)

        for ((index,itemView) in itemViewList.withIndex()){
            itemView.imageView.downloadImage(agentDetail.abilities.get(index).displayIcon,placeHolderProgressBar(requireContext()))
            itemView.abilityName.text=agentDetail.abilities.get(index).displayName
            itemView.abilityDesc.text=agentDetail.abilities.get(index).description
        }
    }
    fun generateRondomList():ArrayList<Int>{
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




}