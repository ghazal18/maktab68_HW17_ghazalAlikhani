package com.example.myapplication.ui.movieList

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.SparseArray
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.MovieRepository
import com.example.myapplication.databinding.FragmentDetailsBinding
import com.example.myapplication.databinding.MovieListItemViewBinding
import com.example.myapplication.network.POSTER_PATH
import com.example.myapplication.network.YOUTUBE_URL
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.Util
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsFragment : Fragment() {
    lateinit var binding: FragmentDetailsBinding
    val viewModel: MovieListViewModel by viewModel()
    val args: DetailsFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details, container, false
        )
        binding.movie = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val movie = args.movie
        viewModel.movieDetail(movie.id)
        try {
            Glide.with(this).load(POSTER_PATH + movie.backdrop_path).into(binding.backdropImageView)
        } catch (e: Exception) {
            Glide.with(this).load(R.drawable.ic_broken_image).into(binding.backdropImageView)
        }
        Glide.with(this).load(POSTER_PATH + movie.poster_path).into(binding.coverImageView)
        viewModel.video(movie.id)
        viewModel.videoList.observe(viewLifecycleOwner) {
            binding.textViewLink.text = "$YOUTUBE_URL${it[0].key}"
        }


    }


}
