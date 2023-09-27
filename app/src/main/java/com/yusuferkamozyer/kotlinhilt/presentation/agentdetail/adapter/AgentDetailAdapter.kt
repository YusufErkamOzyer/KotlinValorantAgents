package com.yusuferkamozyer.kotlinhilt.presentation.agentdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.yusuferkamozyer.kotlinhilt.databinding.RecyclerRowDetailBinding
import com.yusuferkamozyer.kotlinhilt.presentation.model.ViewItem
import com.yusuferkamozyer.kotlinhilt.util.downloadImage
import com.yusuferkamozyer.kotlinhilt.util.placeHolderProgressBar

class AgentDetailAdapter(private val arrayList: ArrayList<ViewItem>): RecyclerView.Adapter<AgentDetailAdapter.AgentDetailHolder>() {
    class AgentDetailHolder(val binding: RecyclerRowDetailBinding): RecyclerView.ViewHolder(binding.root) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentDetailHolder {

        val binding=RecyclerRowDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AgentDetailHolder(binding)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: AgentDetailHolder, position: Int) {
        holder.binding.abilityView.downloadImage(arrayList[position].url, placeHolderProgressBar(holder.itemView.context))
        holder.binding.abilityName.text=arrayList[position].abilityName
        holder.binding.abilityDesc.text=arrayList[position].abilityDesc
    }
}