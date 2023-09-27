package com.yusuferkamozyer.kotlinhilt.presentation.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.yusuferkamozyer.kotlinhilt.data.local.model.AgentDatabase
import com.yusuferkamozyer.kotlinhilt.databinding.RecyclerRowFavoriteBinding
import com.yusuferkamozyer.kotlinhilt.presentation.favorite.view.FavoriteFragmentDirections
import com.yusuferkamozyer.kotlinhilt.presentation.model.ViewItem
import com.yusuferkamozyer.kotlinhilt.util.downloadImage
import com.yusuferkamozyer.kotlinhilt.util.placeHolderProgressBar

class FavoriteAdapter(private val myList: List<AgentDatabase>) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>() {
    class FavoriteHolder(val binding: RecyclerRowFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        val binding =
            RecyclerRowFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteHolder(binding)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.binding.agentAvatarFavorite.downloadImage(
            myList.get(position).displayIcon,
            placeHolderProgressBar(holder.itemView.context)
        )
        holder.binding.agentNameFavorite.text=myList.get(position).displayName
        holder.binding.linearLayoutFav.setOnClickListener {
            val action=FavoriteFragmentDirections.actionFavoriteFragmentToAgentDetailFragment(myList.get(position).uuid)
            Navigation.findNavController(it).navigate(action)
        }
    }
}