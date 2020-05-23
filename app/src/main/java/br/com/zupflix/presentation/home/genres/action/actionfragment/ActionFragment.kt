package br.com.zupflix.presentation.home.genres.action.actionfragment

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
import br.com.zupflix.data.database.model.FavoriteMovies
import br.com.zupflix.presentation.home.genres.action.actionadapter.ActionAdapter
import br.com.zupflix.presentation.home.genres.action.actionviewmodel.ActionViewModel
import kotlinx.android.synthetic.main.fragment_action.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ActionFragment : Fragment() {

    private val viewModel: ActionViewModel by lazy {
        ViewModelProvider(this).get(ActionViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_action, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let { fragmentActivity ->
            viewModel.movieLiveData.observe(fragmentActivity, Observer {
                it?.let {movieList ->
                    with(recyclerViewAction) {
                        layoutManager = GridLayoutManager(fragmentActivity, 2)
                        setHasFixedSize(true)
                        adapter = ActionAdapter(movieList) {movie ->
                            GlobalScope.launch {
                            viewModel.insertMovie(FavoriteMovies(movie.id, movie.originalTitle, movie.voteAverage, movie.genreIds, movie.overview, movie.posterPath, movie.releaseDate))
                            //Toast.makeText(fragmentActivity, "Filme ${movie.originalTitle} inserido com sucesso", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            })
        }

        viewModel.getMoviesByGenres(BuildConfig.API_KEY, "pt-BR", false, 28)
    }
}



