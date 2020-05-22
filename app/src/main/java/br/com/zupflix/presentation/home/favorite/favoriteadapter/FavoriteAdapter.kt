package br.com.zupflix.presentation.home.favorite.favoriteadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zupflix.BuildConfig
import br.com.zupflix.R
import br.com.zupflix.data.database.model.FavoriteMovies
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class FavoriteAdapter(val favoriteMovies: List<FavoriteMovies>) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapterViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return FavoriteAdapterViewHolder(itemView)
    }

    override fun getItemCount() = favoriteMovies.count()

    override fun onBindViewHolder(holder: FavoriteAdapterViewHolder, position: Int) {
        holder.bind(favoriteMovies[position])
    }

    class FavoriteAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textNameMovie = itemView.textNameMovie
        private val textVoteAverage = itemView.textVoteAverage
        private val imageViewMovie = itemView.imageViewMovie
        private val textReleaseDate = itemView.textReleaseDate
        private val picasso = Picasso.get()

        fun bind(movie: FavoriteMovies) {
            textNameMovie.text = movie.originalTitle
            textVoteAverage.text = movie.voteAverage.toString()
            textReleaseDate.text = movie.releaseDate

            movie.posterPath.let {
                picasso.load("""${BuildConfig.BASE_URL_IMAGE}${movie.posterPath}""")
                    .into(imageViewMovie)
            }
        }
    }
}