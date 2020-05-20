package br.com.zupflix.presentation.home.search.searchadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zupflix.BuildConfig
import br.com.zupflix.R
import br.com.zupflix.data.response.SearchMovieResults
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class SearchAdapter(val searchMovies: List<SearchMovieResults>)
    : RecyclerView.Adapter<SearchAdapter.SearchAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return SearchAdapterViewHolder(itemView)
    }

    override fun getItemCount() = searchMovies.count()

    override fun onBindViewHolder(holder: SearchAdapterViewHolder, position: Int) {
        holder.bind(searchMovies[position])
    }

    class SearchAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textNameMovie = itemView.textNameMovie
        private val textVoteAverage = itemView.textVoteAverage
        private val imageViewMovie = itemView.imageViewMovie
        private val textReleaseDate = itemView.textReleaseDate
        private val picasso = Picasso.get()

        fun bind(movie: SearchMovieResults) {
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