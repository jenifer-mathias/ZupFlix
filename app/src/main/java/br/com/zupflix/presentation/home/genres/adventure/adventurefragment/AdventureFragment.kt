package br.com.zupflix.presentation.home.genres.adventure.adventurefragment

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import br.com.zupflix.BuildConfig
import br.com.zupflix.R
import br.com.zupflix.data.database.model.FavoriteMovies
import br.com.zupflix.data.utils.SharedPreference
import br.com.zupflix.databinding.FragmentAdventureBinding
import br.com.zupflix.presentation.home.genres.adventure.adventureadapter.AdventureAdapter
import br.com.zupflix.presentation.home.genres.adventure.adventureviewmodel.AdventureViewModel
import br.com.zupflix.utils.Constants
import br.com.zupflix.utils.Constants.LANGUAGE
import br.com.zupflix.utils.Constants.USER
import br.com.zupflix.utils.viewBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AdventureFragment : Fragment(R.layout.fragment_adventure) {

    private val binding by viewBinding(FragmentAdventureBinding::bind)

    var listFavoriteMovie = listOf<FavoriteMovies>()

    lateinit var userEmail: String

    private val viewModel: AdventureViewModel by lazy {
        ViewModelProvider(this).get(AdventureViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let { fragmentActivity ->
            var sharedPreference = SharedPreference(fragmentActivity)
            sharedPreference.getData(USER)?.let { email ->
                userEmail = email
            }

            viewModel.getFavoriteMovie(userEmail)
                .observe(fragmentActivity, Observer { listMovie ->
                    listMovie?.let { movies ->
                        listFavoriteMovie = movies
                    }
                })

            viewModel.movieLiveData.observe(fragmentActivity, Observer {
                it?.let { movieList ->
                    with(binding.recyclerViewAdventure) {
                        layoutManager = GridLayoutManager(fragmentActivity, 2)
                        setHasFixedSize(true)
                        adapter = AdventureAdapter(movieList, listFavoriteMovie, { movie ->
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
                                Constants.MOVIE.plus(movie.originalTitle)
                                    .plus(Constants.INSERTED_MOVIE),
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
                                Constants.MOVIE.plus(deleteMovie.originalTitle)
                                    .plus(Constants.DELETED_MOVIE),
                                Toast.LENGTH_SHORT
                            ).show()
                        })
                    }
                }
            })
        }
        viewModel.getMoviesByGenres(BuildConfig.API_KEY, LANGUAGE, false, 12)
    }
}


