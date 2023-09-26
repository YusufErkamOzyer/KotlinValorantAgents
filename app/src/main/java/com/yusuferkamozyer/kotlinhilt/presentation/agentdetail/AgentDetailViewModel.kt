package com.yusuferkamozyer.kotlinhilt.presentation.agentdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuferkamozyer.kotlinhilt.data.local.model.AgentDatabase
import com.yusuferkamozyer.kotlinhilt.data.remote.repository.AgentRepositoryImp
import com.yusuferkamozyer.kotlinvalorant.domain.model.AgentDetail
import com.yusuferkamozyer.kotlinvalorant.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
    fun insertAgentDetail(agentDatabase: AgentDatabase){
        repository.saveAgentDetail(agentDatabase)
    }
    fun deleteAgent(uuid:String){
        repository.deleteAgentDetail(uuid)
    }
    fun getFavAgents():List<AgentDatabase>{
        return repository.getMyFavAgents()
    }
    fun deleteAll(){//Test
        repository.deleteAll()
    }

    fun addFavs(currentModel:AgentDatabase){

        CoroutineScope(Dispatchers.IO).launch {
            val myFav=getFavAgents()
            val myFavUuid= arrayListOf<String>()
            for (item in myFav){
                myFavUuid.add(item.uuid)
            }
            if (!myFavUuid.contains(currentModel.uuid)){
                insertAgentDetail(currentModel)
            }

        }
    }
    fun removeFav(currentModel:AgentDatabase){
        CoroutineScope(Dispatchers.IO).launch {
            val myFav=getFavAgents()
            val myFavUuid= arrayListOf<String>()
            for (item in myFav){
                myFavUuid.add(item.uuid)
            }
            if (myFavUuid.contains(currentModel.uuid)){
                deleteAgent(currentModel.uuid)
            }


        }

    }

    fun isContain(agentDetail: AgentDetail):Boolean{
        val myFavUuid= arrayListOf<String>()
        runBlocking {
            launch (Dispatchers.IO){
                val myFav=getFavAgents()
                for (item in myFav){
                    myFavUuid.add(item.uuid)
                }
            }
        }
        return myFavUuid.contains(agentDetail.uuid)
    }


}