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
        var rate=arrayList[position].abilityDesc.length*4/10
        var counter=0
        holder.binding.abilityView.downloadImage(arrayList[position].url, placeHolderProgressBar(holder.itemView.context))
        holder.binding.abilityName.text=arrayList[position].abilityName
        holder.binding.abilityDesc.text=arrayList[position].abilityDesc.substring(0,rate)+".."
        holder.binding.moreLess.setOnClickListener {
            if (counter%2==1){
                holder.binding.abilityDesc.text=arrayList[position].abilityDesc.substring(0,rate)+".."
                holder.binding.moreLess.text="more"
                counter += 1
            }else{
                holder.binding.abilityDesc.text=arrayList[position].abilityDesc
                holder.binding.moreLess.text="less"
                counter += 1

            }

        }


    }
}