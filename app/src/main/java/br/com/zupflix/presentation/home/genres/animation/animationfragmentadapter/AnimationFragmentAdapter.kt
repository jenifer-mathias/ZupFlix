package br.com.zupflix.presentation.home.genres.animation.animationfragmentadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zupflix.BuildConfig
import br.com.zupflix.R
import br.com.zupflix.data.response.MovieResults
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class AnimationFragmentAdapter(val movies: List<MovieResults>)
    : RecyclerView.Adapter<AnimationFragmentAdapter.AdventureAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdventureAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return AdventureAdapterViewHolder(itemView)
    }

    override fun getItemCount() = movies.count()

    override fun onBindViewHolder(holder: AdventureAdapterViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    class AdventureAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
        }
    }
}