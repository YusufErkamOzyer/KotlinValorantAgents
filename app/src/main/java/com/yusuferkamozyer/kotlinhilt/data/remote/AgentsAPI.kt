package com.yusuferkamozyer.kotlinhilt.data.remote

import com.yusuferkamozyer.kotlinvalorant.data.remote.dto.agentsdetaildto.AgentsDetailDTO
import com.yusuferkamozyer.kotlinvalorant.data.remote.dto.agentsdto.AgentsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AgentsAPI {
    //https://valorant-api.com/v1/agents
    @GET("v1/agents")
    suspend fun getAgent():Response<AgentsDTO>
    @GET("v1/agents/{agent_url}")
    suspend fun getAgentDetail(@Path("agent_url") agent_uuid:String):Response<AgentsDetailDTO>

}