package com.yusuferkamozyer.kotlinhilt.agent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuferkamozyer.kotlinhilt.repository.AgentRepository
import com.yusuferkamozyer.kotlinhilt.repository.AgentRepositoryImp
import com.yusuferkamozyer.kotlinvalorant.domain.model.Agent
import com.yusuferkamozyer.kotlinvalorant.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentViewModel @Inject constructor(private val repository: AgentRepositoryImp): ViewModel() {
    private val _agents=MutableLiveData<Resource<List<Agent>>>()
    val agents:LiveData<Resource<List<Agent>>> get() = _agents


    fun getAgents()=viewModelScope.launch {
        repository.getAgent().collect(){
            _agents.value=it
        }
    }


}