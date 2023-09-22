package com.yusuferkamozyer.kotlinhilt.repository

import com.yusuferkamozyer.kotlinvalorant.data.remote.dto.agentsdto.AgentsDTO
import com.yusuferkamozyer.kotlinvalorant.domain.model.Agent
import com.yusuferkamozyer.kotlinvalorant.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AgentRepository {
    suspend fun getAgent(): Flow<Resource<List<Agent>>>
}