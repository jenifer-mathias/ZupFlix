package br.com.zupflix.presentation.home.genres.adventure.adventureadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zupflix.BuildConfig
import br.com.zupflix.data.database.model.FavoriteMovies
import br.com.zupflix.data.results.MovieResults
import br.com.zupflix.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class AdventureAdapter(
    val movies: List<MovieResults>,
    private val checkedMovie: List<FavoriteMovies>,
    private val favoriteClickListener: ((movie: MovieResults) -> Unit),
    private val deleteClickListener: ((movie: MovieResults) -> Unit)
) : RecyclerView.Adapter<AdventureAdapter.AdventureAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdventureAdapterViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdventureAdapterViewHolder(binding, favoriteClickListener, deleteClickListener)
    }

    override fun getItemCount() = movies.count()

    override fun onBindViewHolder(holder: AdventureAdapterViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bind(movies[position], checkedMovie)
    }

    class AdventureAdapterViewHolder(
        private val binding: ItemMovieBinding,
        private val favoriteClickListener: (movie: MovieResults) -> Unit,
        private val deleteClickListener: (movie: MovieResults) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieResults, checkedMovie: List<FavoriteMovies>) {
            binding.textNameMovie.text = movie.originalTitle
            binding.textVoteAverage.text = movie.voteAverage.toString()
            binding.textReleaseDate.text = movie.releaseDate

            for (i in checkedMovie) {
                when {
                    movie.id.equals(i.id) -> binding.imgFavoriteRed.visibility = View.VISIBLE
                }
            }

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