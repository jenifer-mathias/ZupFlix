package br.com.zupflix.presentation.genres.comedy.comedyfragment

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
import br.com.zupflix.presentation.genres.comedy.comedyfragmentadapter.ComedyFragmentAdapter
import br.com.zupflix.presentation.genres.comedy.comedyfragmentviewmodel.ComedyFragmentViewModel
import kotlinx.android.synthetic.main.fragment_comedy.*

class ComedyFragment : Fragment() {

    private val viewModel: ComedyFragmentViewModel by lazy {
        ViewModelProvider(this).get(ComedyFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comedy, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let { fragmentActivity ->
            viewModel.genreMovieLiveData.observe(fragmentActivity, Observer {
                it?.let { movieList ->
                    with(recyclerViewComedy) {
                        layoutManager = GridLayoutManager(fragmentActivity, 2)
                        setHasFixedSize(true)
                        adapter = ComedyFragmentAdapter(movieList)
                    }
                }

            })
        }

        viewModel.getMoviesByGenres(BuildConfig.API_KEY, "pt-BR", false, 35)
    }
}