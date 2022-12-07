package br.com.zupflix.presentation.home.search.searchactivity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import br.com.zupflix.BuildConfig
import br.com.zupflix.R
import br.com.zupflix.data.database.model.FavoriteMovies
import br.com.zupflix.data.utils.SharedPreference
import br.com.zupflix.databinding.ActivitySearchBinding
import br.com.zupflix.presentation.base.BaseActivity
import br.com.zupflix.presentation.home.search.searchadapter.SearchAdapter
import br.com.zupflix.presentation.home.search.searchviewmodel.SearchViewModel
import br.com.zupflix.utils.Constants
import br.com.zupflix.utils.Constants.LANGUAGE
import br.com.zupflix.utils.Constants.MESSAGE_TAG
import br.com.zupflix.utils.Constants.QUERY_TAG
import br.com.zupflix.utils.Constants.USER
import br.com.zupflix.utils.viewBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchActivity : BaseActivity() {

    private val binding by viewBinding(ActivitySearchBinding::inflate)

    var listFavoriteMovies = listOf<FavoriteMovies>()

    lateinit var userEmail: String

    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar(binding.toolbarMovie.toolbar, R.string.txt_search, true)

        var sharedPreference = SharedPreference(this)
        sharedPreference.getData(USER)?.let { email ->
            userEmail = email
        }

        viewModel.getFavoriteMovie(userEmail).observe(this, Observer { listMovie ->
            listMovie?.let { movies ->
                listFavoriteMovies = movies
            }
        })

        viewModel.movieLiveData.observe(this, Observer { searchMovieResults ->
            searchMovieResults?.let { movieList ->
                with(binding.recyclerViewSearch) {
                    layoutManager = GridLayoutManager(this@SearchActivity, 2)
                    adapter = SearchAdapter(movieList, listFavoriteMovies, { movie ->
                        GlobalScope.launch {
                            viewModel.insertMovie(
                                FavoriteMovies(
                                    movie.id,
                                    userEmail,
                                    movie.originalTitle,
                                    movie.voteAverage,
                                    movie.genreIds,
                                    movie.overview,
                                    movie.posterPath,
                                    movie.releaseDate
                                )
                            )
                        }
                        Toast.makeText(
                            this@SearchActivity,
                            Constants.MOVIE.plus(movie.originalTitle)
                                .plus(Constants.INSERTED_MOVIE),
                            Toast.LENGTH_SHORT
                        ).show()
                    }, { deleteMovie ->
                        GlobalScope.launch {
                            viewModel.deleteFavoriteMovie(
                                FavoriteMovies(
                                    deleteMovie.id,
                                    userEmail,
                                    deleteMovie.originalTitle,
                                    deleteMovie.voteAverage,
                                    deleteMovie.genreIds,
                                    deleteMovie.overview,
                                    deleteMovie.posterPath,
                                    deleteMovie.releaseDate
                                )
                            )
                        }
                        Toast.makeText(
                            this@SearchActivity,
                            Constants.MOVIE.plus(deleteMovie.originalTitle)
                                .plus(Constants.DELETED_MOVIE),
                            Toast.LENGTH_SHORT
                        ).show()
                    })
                }
            }
        })

        binding.svSearchMovie.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { query ->
                    viewModel.searchMovies(query, BuildConfig.API_KEY, LANGUAGE, false)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.i(QUERY_TAG, MESSAGE_TAG.plus(newText))
                return true
            }

        })

    }

}



