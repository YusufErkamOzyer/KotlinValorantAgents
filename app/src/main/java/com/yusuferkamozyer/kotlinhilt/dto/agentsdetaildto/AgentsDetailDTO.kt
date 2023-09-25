package com.yusuferkamozyer.kotlinvalorant.data.remote.dto.agentsdetaildto

import com.yusuferkamozyer.kotlinvalorant.domain.model.AgentDetail

data class AgentsDetailDTO(
    val data: Data,
    val status: Int
)

fun AgentsDetailDTO.toAgentDetail():AgentDetail{
    return AgentDetail(data.displayName,data.uuid,data.description,data.abilities,data.background,data.fullPortrait,data.role,data.backgroundGradientColors)
}