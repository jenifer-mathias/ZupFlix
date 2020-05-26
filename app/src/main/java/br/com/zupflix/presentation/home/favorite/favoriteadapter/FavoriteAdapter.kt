package br.com.zupflix.presentation.home.favorite.favoriteadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zupflix.BuildConfig
import br.com.zupflix.R
import br.com.zupflix.data.database.model.FavoriteMovies
import br.com.zupflix.data.results.MovieResults
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class FavoriteAdapter(
    val favoriteMovies: List<FavoriteMovies>,
    val favoriteClickListener: ((movie: FavoriteMovies) -> Unit),
    val deleteClickListener: ((movie: FavoriteMovies) -> Unit)) : RecyclerView.Adapter<FavoriteAdapter.FavoriteAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapterViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return FavoriteAdapterViewHolder(itemView, favoriteClickListener, deleteClickListener)
    }

    override fun getItemCount() = favoriteMovies.count()

    override fun onBindViewHolder(holder: FavoriteAdapterViewHolder, position: Int) {
        holder.bind(favoriteMovies[position])
    }

    class FavoriteAdapterViewHolder(itemView: View,
                                    private val favoriteClickListener: (movie: FavoriteMovies) -> Unit,
                                    private val deleteClickListener: (movie: FavoriteMovies) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val textNameMovie = itemView.textNameMovie
        private val textVoteAverage = itemView.textVoteAverage
        private val imageViewMovie = itemView.imageViewMovie
        private val textReleaseDate = itemView.textReleaseDate
        private val imageViewFavoriteMovie = itemView.img_favorite_movie
        private val imageViewFavoriteRed = itemView.img_favorite_red
        private val picasso = Picasso.get()

        fun bind(movie: FavoriteMovies) {
            textNameMovie.text = movie.originalTitle
            textVoteAverage.text = movie.voteAverage.toString()
            textReleaseDate.text = movie.releaseDate

            imageViewFavoriteMovie.visibility = View.GONE
            imageViewFavoriteRed.visibility = View.VISIBLE

            movie.posterPath.let {
                picasso.load("""${BuildConfig.BASE_URL_IMAGE}${movie.posterPath}""")
                    .into(imageViewMovie)
            }

            imageViewFavoriteMovie.setOnClickListener {
                favoriteClickListener.invoke(movie)
                imageViewFavoriteMovie.visibility = View.GONE
                imageViewFavoriteRed.visibility = View.VISIBLE
            }

            imageViewFavoriteRed.setOnClickListener {
                deleteClickListener.invoke(movie)
                imageViewFavoriteMovie.visibility = View.VISIBLE
                imageViewFavoriteRed.visibility = View.GONE
            }

        }
    }
}