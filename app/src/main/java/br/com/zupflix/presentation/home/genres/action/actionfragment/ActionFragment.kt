package br.com.zupflix.presentation.home.genres.action.actionfragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import br.com.zupflix.BuildConfig
import br.com.zupflix.R
import br.com.zupflix.data.database.model.FavoriteMovies
import br.com.zupflix.data.utils.SharedPreference
import br.com.zupflix.databinding.FragmentActionBinding
import br.com.zupflix.presentation.home.genres.action.actionadapter.ActionAdapter
import br.com.zupflix.presentation.home.genres.action.actionviewmodel.ActionViewModel
import br.com.zupflix.utils.Constants.DELETED_MOVIE
import br.com.zupflix.utils.Constants.INSERTED_MOVIE
import br.com.zupflix.utils.Constants.LANGUAGE
import br.com.zupflix.utils.Constants.MOVIE
import br.com.zupflix.utils.Constants.USER
import br.com.zupflix.utils.viewBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ActionFragment : Fragment(R.layout.fragment_action) {

    private val binding by viewBinding(FragmentActionBinding::bind)

    var listFavoriteMovies = listOf<FavoriteMovies>()

    lateinit var userEmail: String

    private val viewModel: ActionViewModel by lazy {
        ViewModelProvider(this).get(ActionViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let { fragmentActivity ->

            var sharedPreference = SharedPreference(fragmentActivity)
            sharedPreference.getData(USER)?.let { email ->
                userEmail = email
            }

            viewModel.getFavoriteMovie(userEmail).observe(fragmentActivity, Observer { listMovie ->
                listMovie?.let { movies ->
                    listFavoriteMovies = movies
                }
            })

            viewModel.isLoading.observe(fragmentActivity, Observer {
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            })

            viewModel.movieLiveData.observe(fragmentActivity, Observer {
                it?.let { movieList ->
                    with(binding.recyclerViewAction) {
                        layoutManager = GridLayoutManager(fragmentActivity, 2)
                        setHasFixedSize(true)
                        adapter = ActionAdapter(movieList, listFavoriteMovies, { movie ->
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
                                fragmentActivity,
                                MOVIE.plus(movie.originalTitle).plus(INSERTED_MOVIE),
                                Toast.LENGTH_SHORT
                            ).show()
                        }, { deleteMovie ->
                            GlobalScope.launch {
                                viewModel.deleteMovie(
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
                                fragmentActivity,
                                MOVIE.plus(deleteMovie.originalTitle).plus(DELETED_MOVIE),
                                Toast.LENGTH_SHORT
                            ).show()
                        })
                    }
                }
            })
        }
        viewModel.getMoviesByGenres(BuildConfig.API_KEY, LANGUAGE, false, 28)
    }
}



