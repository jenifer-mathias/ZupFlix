package br.com.zupflix.presentation.home.genres.action.actionadapter

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

class ActionAdapter(val movies: List<MovieResults>,
                    val checkedMovie: List<FavoriteMovies>,
                    val favoriteClickListener: ((movie: MovieResults) -> Unit),
                    val deleteClickListener: ((movie: MovieResults) -> Unit))
    : RecyclerView.Adapter<ActionAdapter.ActionAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ActionAdapterViewHolder(itemView, checkedMovie, favoriteClickListener, deleteClickListener)
    }

    override fun getItemCount() = movies.count()

    override fun onBindViewHolder(holder: ActionAdapterViewHolder, position: Int) {
        holder.bind(movies[position], checkedMovie)
    }

    class ActionAdapterViewHolder(itemView: View,
                                  private val checkedMovie: List<FavoriteMovies>,
                                  private val favoriteClickListener: (movie: MovieResults) -> Unit,
                                  private val deleteClickListener: (movie: MovieResults) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val textNameMovie = itemView.textNameMovie
        private val textVoteAverage = itemView.textVoteAverage
        private val imageViewMovie = itemView.imageViewMovie
        private val textReleaseDate = itemView.textReleaseDate
        private val imageViewFavoriteMovie = itemView.img_favorite_movie
        private val imageViewFavoriteRed = itemView.img_favorite_red
        private val picasso = Picasso.get()

        fun bind(movie: MovieResults, checkedMovie: List<FavoriteMovies>) {
            textNameMovie.text = movie.originalTitle
            textVoteAverage.text = movie.voteAverage.toString()
            textReleaseDate.text = movie.releaseDate

           for (i in checkedMovie) {
              if( i.id.equals(movie.id)) {
                  imageViewFavoriteMovie.visibility = View.GONE
                  imageViewFavoriteRed.visibility = View.VISIBLE
              }
           }

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