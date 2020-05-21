package br.com.zupflix.presentation.home.search.searchactivity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import br.com.zupflix.BuildConfig
import br.com.zupflix.R
import br.com.zupflix.presentation.base.BaseActivity
import br.com.zupflix.presentation.home.search.searchadapter.SearchAdapter
import br.com.zupflix.presentation.home.search.searchviewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.toolbar.*

class SearchActivity : BaseActivity() {

    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setupToolbar(toolbarMovie, R.string.search, true)

        viewModel.movieMovieLiveData.observe(this, Observer { searchMovieResults ->
            searchMovieResults?.let { movieList ->
                with(recyclerViewSearch) {
                    layoutManager = GridLayoutManager(this@SearchActivity, 1)
                    setHasFixedSize(true)
                    adapter = SearchAdapter(movieList)
                }
            }
        })

        svSearchMovie.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { query ->
                    viewModel.searchMovies(query, BuildConfig.API_KEY, "pt-BR", false)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.i("QUERY", "Palavra enviada para request -> $newText")
                return true
            }

        })

    }

}



