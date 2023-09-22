package com.yusuferkamozyer.kotlinhilt.agent

import com.yusuferkamozyer.kotlinvalorant.domain.model.Agent

class AgentState(
    val isLoading: Boolean =false,
    val error: String ="",
    val agentList: List<Agent> = emptyList()
) {
}