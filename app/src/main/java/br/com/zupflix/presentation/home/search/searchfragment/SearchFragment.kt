package br.com.zupflix.presentation.home.search.searchfragment

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
import br.com.zupflix.presentation.home.search.searchadapter.SearchAdapter
import br.com.zupflix.presentation.home.search.searchviewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let { fragmentActivity ->
            viewModel.movieMovieLiveData.observe(fragmentActivity, Observer {
                it?.let {movieList ->
                    with(recyclerViewSearch) {
                        layoutManager = GridLayoutManager(fragmentActivity, 1)
                        setHasFixedSize(true)
                        adapter = SearchAdapter(movieList)
                    }
                }

            })
        }

        viewModel.searchMovies(BuildConfig.API_KEY, "pt-BR", edtSearchMovie.text.toString(), false)
    }
}

