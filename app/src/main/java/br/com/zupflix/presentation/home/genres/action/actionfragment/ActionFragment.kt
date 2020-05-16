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
import br.com.zupflix.presentation.home.genres.action.actionfragmentadapter.ActionFragmentAdapter
import br.com.zupflix.presentation.home.genres.action.actionfragmentviewmodel.ActionFragmentViewModel
import kotlinx.android.synthetic.main.fragment_action.*

class ActionFragment : Fragment() {

    private val viewModel: ActionFragmentViewModel by lazy {
        ViewModelProvider(this).get(ActionFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_action, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let { fragmentActivity ->
            viewModel.movieMovieLiveData.observe(fragmentActivity, Observer {
                it?.let {movieList ->
                    with(recyclerViewAction) {
                        layoutManager = GridLayoutManager(fragmentActivity, 2)
                        setHasFixedSize(true)
                        adapter = ActionFragmentAdapter(movieList)
                    }
                }

            })
        }

        viewModel.getMoviesByGenres(BuildConfig.API_KEY, "pt-BR", false, 28)
    }
}


