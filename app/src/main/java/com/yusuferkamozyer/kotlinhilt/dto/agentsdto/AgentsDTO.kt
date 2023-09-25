package com.yusuferkamozyer.kotlinvalorant.data.remote.dto.agentsdto

import com.yusuferkamozyer.kotlinvalorant.domain.model.Agent


data class AgentsDTO(
    val data: List<Data>,
    val status: Int
)

fun AgentsDTO.toAgent():List<Agent>{
    return data.map { data -> Agent(data.uuid,data.displayName,data.displayIcon,data.isPlayableCharacter) }
}
