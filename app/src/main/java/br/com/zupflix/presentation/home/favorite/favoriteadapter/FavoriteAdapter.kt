package br.com.zupflix.presentation.home.favorite.favoriteadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zupflix.BuildConfig
import br.com.zupflix.data.database.model.FavoriteMovies
import br.com.zupflix.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class FavoriteAdapter(
    val favoriteMovies: List<FavoriteMovies>,
    val favoriteClickListener: ((movie: FavoriteMovies) -> Unit),
    val deleteClickListener: ((movie: FavoriteMovies) -> Unit)
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapterViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteAdapterViewHolder(binding, favoriteClickListener, deleteClickListener)
    }

    override fun getItemCount() = favoriteMovies.count()

    override fun onBindViewHolder(holder: FavoriteAdapterViewHolder, position: Int) {
        holder.bind(favoriteMovies[position])
    }

    class FavoriteAdapterViewHolder(
        private val binding: ItemMovieBinding,
        private val favoriteClickListener: (movie: FavoriteMovies) -> Unit,
        private val deleteClickListener: (movie: FavoriteMovies) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: FavoriteMovies) {
            binding.textNameMovie.text = movie.originalTitle
            binding.textVoteAverage.text = movie.voteAverage.toString()
            binding.textReleaseDate.text = movie.releaseDate

            binding.imgFavoriteMovie.visibility = View.GONE
            binding.imgFavoriteRed.visibility = View.VISIBLE

            movie.posterPath.let {
                Picasso.get().load("""${BuildConfig.BASE_URL_IMAGE}${movie.posterPath}""")
                    .into(binding.imageViewMovie)
            }

            binding.imgFavoriteMovie.setOnClickListener {
                favoriteClickListener.invoke(movie)
                binding.imgFavoriteMovie.visibility = View.GONE
                binding.imgFavoriteRed.visibility = View.VISIBLE
            }

            binding.imgFavoriteRed.setOnClickListener {
                deleteClickListener.invoke(movie)
                binding.imgFavoriteMovie.visibility = View.VISIBLE
                binding.imgFavoriteRed.visibility = View.GONE
            }

        }
    }
}