package com.yusuferkamozyer.kotlinhilt.data.local.repository

import com.yusuferkamozyer.kotlinhilt.data.local.model.AgentDatabase

interface AgentLocalRepository {
    fun saveAgentDetail(agentDatabase: AgentDatabase)
    fun deleteAgentDetail(uuid:String)
    fun getMyFavAgents():List<AgentDatabase>
    fun deleteAll()
}