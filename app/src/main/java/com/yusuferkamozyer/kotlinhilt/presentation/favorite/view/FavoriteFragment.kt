package com.yusuferkamozyer.kotlinhilt.presentation.favorite.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.yusuferkamozyer.kotlinhilt.MainActivity
import com.yusuferkamozyer.kotlinhilt.databinding.FragmentFavoriteBinding
import com.yusuferkamozyer.kotlinhilt.presentation.favorite.FavoriteViewModel
import com.yusuferkamozyer.kotlinhilt.presentation.favorite.adapter.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private val viewModel: FavoriteViewModel by viewModels()
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private var activity: MainActivity?=null
    private lateinit var favoriteAdapter: FavoriteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity=activity
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()



    }
    private fun getData(){
        val myFavAgents=viewModel.getMyFavoriteAgents()
        println(myFavAgents)
        val favoriteAdapter=FavoriteAdapter(myFavAgents)
        binding.recyclerViewFavorites.layoutManager=GridLayoutManager(requireContext(),3)
        binding.recyclerViewFavorites.adapter=favoriteAdapter
    }


}