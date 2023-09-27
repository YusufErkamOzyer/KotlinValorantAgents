package com.yusuferkamozyer.kotlinhilt.presentation.agent.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.yusuferkamozyer.kotlinhilt.R
import com.yusuferkamozyer.kotlinhilt.databinding.RecyclerRowBinding
import com.yusuferkamozyer.kotlinhilt.presentation.agent.view.AgentFragmentDirections
import com.yusuferkamozyer.kotlinhilt.util.downloadImage
import com.yusuferkamozyer.kotlinhilt.util.placeHolderProgressBar
import com.yusuferkamozyer.kotlinvalorant.domain.model.Agent

class AgentsAdapter(private val agentsList: ArrayList<Agent>): RecyclerView.Adapter<AgentsAdapter.AgentsHolder>() {
    class AgentsHolder(val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentsHolder {
        val binding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AgentsHolder(binding)
    }

    override fun getItemCount(): Int {
        return agentsList.size
    }

    override fun onBindViewHolder(holder: AgentsHolder, position: Int) {
        holder.binding.agentName.text=agentsList.get(position).displayName
        holder.binding.agentName.setTextColor(Color.parseColor("#FFFFFFFF"))
        holder.binding.agentAvatar.downloadImage(agentsList.get(position).displayIcon,
            placeHolderProgressBar(holder.itemView.context)
        )
        holder.binding.linearLayout.setOnClickListener {
            println(agentsList.get(position).displayName)
            val action=AgentFragmentDirections.actionAgentFragmentToAgentDetailFragment(agentID = agentsList.get(position).uuid)
            Navigation.findNavController(it).navigate(action)
        }
    }
    fun uptade(newAgentList: List<Agent>){
        agentsList.clear()
        agentsList.addAll(newAgentList)
        notifyDataSetChanged()
    }
}