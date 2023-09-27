package com.yusuferkamozyer.kotlinhilt.presentation.agent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuferkamozyer.kotlinhilt.data.remote.repository.AgentRemoteRepositoryImp
import com.yusuferkamozyer.kotlinvalorant.domain.model.Agent
import com.yusuferkamozyer.kotlinvalorant.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentViewModel @Inject constructor(private val agentRemoteRepository: AgentRemoteRepositoryImp) : ViewModel() {
    private val _agents = MutableLiveData<Resource<List<Agent>>>()
    val agents: LiveData<Resource<List<Agent>>> get() = _agents


    fun getAgents() = viewModelScope.launch {
        agentRemoteRepository.getAgent()
            .collect() {
            _agents.value = it
        }
    }


}