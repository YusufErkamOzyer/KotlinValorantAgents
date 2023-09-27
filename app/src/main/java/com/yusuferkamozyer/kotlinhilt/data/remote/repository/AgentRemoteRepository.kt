package com.yusuferkamozyer.kotlinhilt.data.remote.repository

import com.yusuferkamozyer.kotlinhilt.data.local.model.AgentDatabase
import com.yusuferkamozyer.kotlinvalorant.domain.model.Agent
import com.yusuferkamozyer.kotlinvalorant.domain.model.AgentDetail
import com.yusuferkamozyer.kotlinvalorant.util.Resource
import kotlinx.coroutines.flow.Flow

interface AgentRemoteRepository {
    suspend fun getAgent(): Flow<Resource<List<Agent>>>
    suspend fun getAgentDetail(agent_uuid:String):Flow<Resource<AgentDetail>>


}