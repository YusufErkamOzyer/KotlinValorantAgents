package com.yusuferkamozyer.kotlinhilt.data.local.repository

import androidx.room.Dao
import com.yusuferkamozyer.kotlinhilt.data.local.AgentDetailDao
import com.yusuferkamozyer.kotlinhilt.data.local.model.AgentDatabase
import javax.inject.Inject

class AgentLocalRepositoryImp @Inject constructor(private val dao: AgentDetailDao):AgentLocalRepository {
    override fun saveAgentDetail(agentDatabase: AgentDatabase) {
        dao.insertAgentDetail(agentDatabase)
    }

    override fun deleteAgentDetail(uuid: String) {
        dao.deleteAgentDetail(uuid)
    }

    override fun getMyFavAgents(): List<AgentDatabase> {
        return dao.getAll()
    }

    override fun deleteAll() {
        dao.deleteAll()
    }
}