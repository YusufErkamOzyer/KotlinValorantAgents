package com.yusuferkamozyer.kotlinvalorant.domain.model

import com.yusuferkamozyer.kotlinvalorant.data.remote.dto.agentsdetaildto.Ability
import com.yusuferkamozyer.kotlinvalorant.data.remote.dto.agentsdetaildto.Role

class AgentDetail(
    val displayName: String,
    val uuid: String,
    val description: String,
    val abilities: List<Ability>,
    val background: String,
    val fullPortrait: String,
    val role: Role,
    val backgroundGradientColors: List<String>
    ) {
}