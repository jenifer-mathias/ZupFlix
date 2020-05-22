package br.com.zupflix.presentation.home.favorite.favoriteactivity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import br.com.zupflix.R
import br.com.zupflix.presentation.base.BaseActivity
import br.com.zupflix.presentation.home.favorite.favoriteadapter.FavoriteAdapter
import br.com.zupflix.presentation.home.favorite.favoriteviewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.item_movie.*
import kotlinx.android.synthetic.main.toolbar.*

class FavoriteActivity : BaseActivity() {

    private val viewModel: FavoriteViewModel by lazy {
        ViewModelProvider(this).get((FavoriteViewModel::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        setupToolbar(toolbarMovie, R.string.txt_favorite_movies, true)

        viewModel.getFavoriteMovie().observe(this, Observer { favoriteMovies ->
            favoriteMovies?.let { favoriteList ->
                with(recylerViewFavorite) {
                    layoutManager = GridLayoutManager(this@FavoriteActivity, 2)
                    setHasFixedSize(true)
                    adapter = FavoriteAdapter(favoriteList)
                }
            }
        })
    }
}
