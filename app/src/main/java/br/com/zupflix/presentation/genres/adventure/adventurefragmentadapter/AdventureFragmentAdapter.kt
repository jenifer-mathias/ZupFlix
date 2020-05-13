package br.com.zupflix.presentation.genres.adventure.adventurefragmentadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zupflix.BuildConfig
import br.com.zupflix.R
import br.com.zupflix.data.response.GenreResults
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class AdventureFragmentAdapter(val genres: List<GenreResults>)
    : RecyclerView.Adapter<AdventureFragmentAdapter.AdventureAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdventureAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return AdventureAdapterViewHolder(itemView)
    }

    override fun getItemCount() = genres.count()

    override fun onBindViewHolder(holder: AdventureAdapterViewHolder, position: Int) {
        holder.bind(genres[position])
    }

    class AdventureAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textNameMovie = itemView.textNameMovie
        private val textVoteAverage = itemView.textVoteAverage
        private val imageViewMovie = itemView.imageViewMovie
        private val textReleaseDate = itemView.textReleaseDate
        private val picasso = Picasso.get()

        fun bind(genre: GenreResults) {
            textNameMovie.text = genre.originalTitle
            textVoteAverage.text = genre.voteAverage.toString()
            textReleaseDate.text = genre.releaseDate

            genre.posterPath.let {
                picasso.load("""${BuildConfig.BASE_URL_IMAGE}${genre.posterPath}""")
                    .into(imageViewMovie)
            }
        }
    }
}