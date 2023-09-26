package com.yusuferkamozyer.kotlinhilt.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yusuferkamozyer.kotlinhilt.data.local.model.AgentDatabase
import com.yusuferkamozyer.kotlinvalorant.domain.model.AgentDetail

@Database(entities = [AgentDatabase::class], version = 1)
abstract class AgentFavDatabase(): RoomDatabase() {
    abstract fun agentDetailDao():AgentDetailDao
}