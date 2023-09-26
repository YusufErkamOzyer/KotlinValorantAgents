package com.yusuferkamozyer.kotlinhilt.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agentdetail")
class AgentDatabase(
    @ColumnInfo(name = "agent_name")
    val displayName: String,
    @ColumnInfo(name = "agent_uuid")
    val uuid: String,
    @ColumnInfo(name = "agent_display_icon")
    val displayIcon: String
) {
    @PrimaryKey(autoGenerate = true)
    var id=0
}