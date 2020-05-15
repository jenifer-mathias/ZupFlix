package br.com.zupflix.presentation.home.genres.adventure.adventurefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import br.com.zupflix.BuildConfig
import br.com.zupflix.R
import br.com.zupflix.presentation.home.genres.adventure.adventurefragmentadapter.AdventureFragmentAdapter
import br.com.zupflix.presentation.home.genres.adventure.adventurefragmentviewmodel.AdventureFragmentViewModel
import kotlinx.android.synthetic.main.fragment_adventure.*

class AdventureFragment : Fragment() {

    private val viewModel: AdventureFragmentViewModel by lazy {
        ViewModelProvider(this).get(AdventureFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_adventure, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let { fragmentActivity ->
            viewModel.movieMovieLiveData.observe(fragmentActivity, Observer {
                it?.let {movieList ->
                    with(recyclerViewAdventure) {
                        layoutManager = GridLayoutManager(fragmentActivity, 2)
                        setHasFixedSize(true)
                        adapter = AdventureFragmentAdapter(movieList)
                    }
                }

            })
        }

        viewModel.getMoviesByGenres(BuildConfig.API_KEY, "pt-BR", false, 12)
    }
}


