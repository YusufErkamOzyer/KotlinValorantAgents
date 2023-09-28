package com.yusuferkamozyer.kotlinhilt.presentation.agent.view

import android.opengl.Visibility
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusuferkamozyer.kotlinhilt.MainActivity
import com.yusuferkamozyer.kotlinhilt.R
import com.yusuferkamozyer.kotlinhilt.databinding.FragmentAgentBinding
import com.yusuferkamozyer.kotlinhilt.presentation.agent.AgentViewModel
import com.yusuferkamozyer.kotlinhilt.presentation.agent.adapter.AgentsAdapter
import com.yusuferkamozyer.kotlinhilt.util.afterTextChanged
import com.yusuferkamozyer.kotlinhilt.util.upperExtensions
import com.yusuferkamozyer.kotlinvalorant.domain.model.Agent
import com.yusuferkamozyer.kotlinvalorant.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Error
import java.util.ArrayList
import javax.inject.Inject

@AndroidEntryPoint
class AgentFragment @Inject constructor() : Fragment() {
    private var activity: MainActivity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = activity

    }

    private lateinit var agentList: ArrayList<Agent>
    private var _binding: FragmentAgentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAgentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    private val myList = arrayListOf<Agent>()
    private val viewModel: AgentViewModel by viewModels()
    private var myAdapter: AgentsAdapter = AgentsAdapter(arrayListOf())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAgents()
        binding.agentRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.agentRecyclerView.adapter = myAdapter
        binding.searchBar.afterTextChanged {
            searchString(it)
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.agents.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    agentList = it.data?.let { it1 -> ArrayList(it1) }!!
                    myAdapter.uptade(agentList)
                    binding.agentRecyclerView.adapter = myAdapter
                    stateController(success = true, error = false, loading = false)
                }

                is Resource.Loading -> {
                    stateController(success = false, error = false, loading = true)

                }

                is Resource.Error -> {
                    binding.textViewError.text = it.message
                    stateController(success = false, error = true, loading = false)
                }
            }

        })

    }

    private fun stateController(success: Boolean, error: Boolean, loading: Boolean) {
        binding.agentRecyclerView.isVisible = success
        binding.textViewError.isVisible = error
        binding.progressBar.isVisible = loading

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    private fun searchString(str: String) {
        var string = str.upperExtensions()
        myList.clear()
        println(string.length)
        try {
            for (item in agentList) {
                if (string in item.displayName) {
                    myList.add(item)
                }
            }
            myAdapter.uptade(myList)

        } catch (e: Exception) {
            println(e.localizedMessage)
        }
    }
}