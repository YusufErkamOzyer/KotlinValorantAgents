package com.yusuferkamozyer.kotlinhilt.presentation.favorite

import androidx.lifecycle.ViewModel
import com.yusuferkamozyer.kotlinhilt.data.local.model.AgentDatabase
import com.yusuferkamozyer.kotlinhilt.data.local.repository.AgentLocalRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
@HiltViewModel
class FavoriteViewModel @Inject constructor(private val agentLocalRepository: AgentLocalRepositoryImp) :
    ViewModel() {
    private val exceptionHandler= CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
    }
    fun insertAgentDetail(agentDatabase: AgentDatabase) {
        agentLocalRepository.saveAgentDetail(agentDatabase)
    }

    fun deleteAgent(uuid: String) {
        agentLocalRepository.deleteAgentDetail(uuid)
    }

    fun getFavAgents(): List<AgentDatabase> {
        return agentLocalRepository.getMyFavAgents()
    }

    fun deleteAll() {//Test
        agentLocalRepository.deleteAll()
    }
    fun getMyFavoriteAgents(): List<AgentDatabase> {
        var myFavAgents= emptyList<AgentDatabase>()
        runBlocking {
            launch (Dispatchers.IO){
                myFavAgents=getFavAgents()
            }
        }
        return myFavAgents
    }
    fun trying(){
        println("deneme")
    }


}