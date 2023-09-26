package com.yusuferkamozyer.kotlinhilt.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yusuferkamozyer.kotlinhilt.data.local.model.AgentDatabase
import com.yusuferkamozyer.kotlinvalorant.domain.model.AgentDetail

@Dao
interface AgentDetailDao {
    @Insert
    fun insertAgentDetail(vararg agentDatabase: AgentDatabase)

    @Query("SELECT * FROM agentdetail")
    fun getAll():List<AgentDatabase>

    @Query("DELETE FROM agentdetail WHERE agent_uuid=:uuid")
    fun deleteAgentDetail(uuid:String)

    @Query("DELETE FROM agentdetail")
    fun deleteAll()
}