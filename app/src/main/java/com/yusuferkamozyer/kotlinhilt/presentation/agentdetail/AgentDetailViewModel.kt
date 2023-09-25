package com.yusuferkamozyer.kotlinhilt.presentation.agentdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuferkamozyer.kotlinhilt.repository.AgentRepositoryImp
import com.yusuferkamozyer.kotlinvalorant.domain.model.AgentDetail
import com.yusuferkamozyer.kotlinvalorant.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentDetailViewModel @Inject constructor(private val repository: AgentRepositoryImp): ViewModel() {

    private val _state=MutableLiveData<Resource<AgentDetail>>()
    val state :LiveData<Resource<AgentDetail>> get() =_state

    fun getAgentDetail(agentuuid:String)=viewModelScope.launch {
        repository.getAgentDetail(agentuuid).collect(){
            _state.value=it
        }
    }


}