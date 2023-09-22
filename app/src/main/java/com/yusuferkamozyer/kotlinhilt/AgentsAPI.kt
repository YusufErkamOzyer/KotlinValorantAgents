package com.yusuferkamozyer.kotlinhilt

import com.yusuferkamozyer.kotlinvalorant.data.remote.dto.agentsdetaildto.AgentsDetailDTO
import com.yusuferkamozyer.kotlinvalorant.data.remote.dto.agentsdto.AgentsDTO
import retrofit2.Response
import retrofit2.http.GET

interface AgentsAPI {
    //https://valorant-api.com/v1/agents
    @GET("v1/agents")
    suspend fun getAgent():Response<AgentsDTO>
    /*@GET()
    suspend fun getAgentDetail():AgentsDetailDTO*/

}