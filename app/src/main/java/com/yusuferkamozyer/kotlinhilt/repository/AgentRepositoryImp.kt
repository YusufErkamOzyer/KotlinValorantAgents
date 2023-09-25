package com.yusuferkamozyer.kotlinhilt.repository

import com.yusuferkamozyer.kotlinhilt.AgentsAPI
import com.yusuferkamozyer.kotlinvalorant.data.remote.dto.agentsdetaildto.toAgentDetail
import com.yusuferkamozyer.kotlinvalorant.data.remote.dto.agentsdto.AgentsDTO
import com.yusuferkamozyer.kotlinvalorant.data.remote.dto.agentsdto.toAgent
import com.yusuferkamozyer.kotlinvalorant.domain.model.Agent
import com.yusuferkamozyer.kotlinvalorant.domain.model.AgentDetail
import com.yusuferkamozyer.kotlinvalorant.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOError
import javax.inject.Inject

class AgentRepositoryImp @Inject constructor(private val api:AgentsAPI):AgentRepository {
     override suspend fun getAgent(): Flow<Resource<List<Agent>>> = flow {
        try {
            emit(Resource.Loading())
            val response=api.getAgent()
            emit(Resource.Success(data =response.body()?.toAgent() ?: emptyList() ))
        }catch (e:Exception){
            emit(Resource.Error(e.localizedMessage?:"Error!"))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Could not reach internet"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getAgentDetail(agent_uuid: String): Flow<Resource<AgentDetail>> = flow<Resource<AgentDetail>> {
        try {
            emit(Resource.Loading())
            val response=api.getAgentDetail(agent_uuid)
            emit(Resource.Success(data = response.body()?.toAgentDetail()!!))
        }catch (e:Exception){
            emit(Resource.Error(e.localizedMessage?:"Error!"))
        }catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        }catch (e: IOError) {
            emit(Resource.Error(message = "Could not reach internet"))
        }

    }.flowOn(Dispatchers.IO)





}