package br.com.zupflix.presentation.genres.animation.animationfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.zupflix.BuildConfig
import br.com.zupflix.R
import br.com.zupflix.presentation.genres.animation.animationfragmentviewmodel.AnimationFragmentViewModel
import kotlinx.android.synthetic.main.fragment_animation.*

class AnimationFragment : Fragment() {

    private val viewModel: AnimationFragmentViewModel by lazy {
        ViewModelProvider(this).get(AnimationFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_animation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let { fragmentActivity ->
            viewModel.genreMovieLiveData.observe(fragmentActivity, Observer {
                it?.let {movieList ->
                    with(recyclerViewAnimation) {
                        layoutManager = GridLayoutManager(fragmentActivity, 2)
                        setHasFixedSize(true)
                        adapter = AnimationFragmentAdapter(movieList)
                    }
                }

            })
        }

        viewModel.getMoviesByGenres(BuildConfig.API_KEY, "pt-BR", false, 16)
    }
}


