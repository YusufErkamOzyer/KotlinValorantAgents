package com.yusuferkamozyer.kotlinvalorant.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.yusuferkamozyer.kotlinvalorant.data.remote.dto.agentsdetaildto.Ability
import com.yusuferkamozyer.kotlinvalorant.data.remote.dto.agentsdetaildto.Role

class AgentDetail(

    val displayName: String,
    val uuid: String,
    val description: String,
    val abilities: List<Ability>,
    val fullPortrait: String,
    val role: Role,
    val displayIcon: String,
    val backgroundGradientColors: List<String>

    ) {
}