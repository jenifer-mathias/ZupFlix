package br.com.zupflix.presentation.home.search.searchadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zupflix.BuildConfig
import br.com.zupflix.data.database.model.FavoriteMovies
import br.com.zupflix.data.results.SearchMovieResults
import br.com.zupflix.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class SearchAdapter(
    val searchMovies: List<SearchMovieResults>,
    private val checkedMovie: List<FavoriteMovies>,
    private val favoriteClickListener: ((movie: SearchMovieResults) -> Unit),
    private val deleteClickListener: ((movie: SearchMovieResults) -> Unit)
) : RecyclerView.Adapter<SearchAdapter.SearchAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapterViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchAdapterViewHolder(binding, favoriteClickListener, deleteClickListener)
    }

    override fun getItemCount() = searchMovies.count()

    override fun onBindViewHolder(holder: SearchAdapterViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bind(searchMovies[position], checkedMovie)
    }

    class SearchAdapterViewHolder(
        private val binding: ItemMovieBinding,
        private val favoriteClickListener: (movie: SearchMovieResults) -> Unit,
        private val deleteClickListener: (movie: SearchMovieResults) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: SearchMovieResults, checkedMovie: List<FavoriteMovies>) {
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