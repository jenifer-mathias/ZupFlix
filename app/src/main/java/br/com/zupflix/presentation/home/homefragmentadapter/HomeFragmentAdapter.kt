package br.com.zupflix.presentation.home.homefragmentadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zupflix.BuildConfig
import br.com.zupflix.R
import br.com.zupflix.data.response.MovieResults
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class HomeFragmentAdapter(
    val movies: List<MovieResults>,
    val itemClickListener: ((movieResult: MovieResults) -> Unit)
) : RecyclerView.Adapter<HomeFragmentAdapter.HomeAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapterViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return HomeAdapterViewHolder(itemView, itemClickListener)
    }

    override fun getItemCount() = movies.count()

    override fun onBindViewHolder(holder: HomeAdapterViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    class HomeAdapterViewHolder(itemView: View,
        private val itemClickListener: (movieResult: MovieResults) -> Unit) : RecyclerView.ViewHolder(itemView) {

        private val textNameMovie = itemView.textNameMovie
        private val textVoteAverage = itemView.textVoteAverage
        private val imageViewMovie = itemView.imageViewMovie
        private val textReleaseDate = itemView.textReleaseDate
        private val picasso = Picasso.get()

        fun bind(movie: MovieResults) {

            textNameMovie.text = movie.originalTitle
            textVoteAverage.text = movie.voteAverage.toString()
            textReleaseDate.text = movie.releaseDate

            movie.posterPath.let {
                picasso.load("""${BuildConfig.BASE_URL_IMAGE}${movie.posterPath}""")
                    .into(imageViewMovie)
            }

            itemView.setOnClickListener {
                itemClickListener.invoke(movie)
            }
        }
    }
}
