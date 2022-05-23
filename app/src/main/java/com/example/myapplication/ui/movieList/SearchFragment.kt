package com.example.myapplication.ui.movieList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    val viewModel: MovieListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search, container, false
        )
        binding.vModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = MovieAdaptor(){movie->
            val action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(movie)
            findNavController().navigate(action)
        }
        binding.searchButton.setOnClickListener {
            viewModel.searchMovie(binding.searchEditText.text.toString())
            viewModel.searchMovieList.observe(viewLifecycleOwner) {
                binding.movieRecyclerView.adapter = adapter
                adapter.submitList(it)
            }
        }
    }


}