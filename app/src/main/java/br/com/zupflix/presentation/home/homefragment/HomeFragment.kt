import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import br.com.zupflix.BuildConfig
import br.com.zupflix.R
import br.com.zupflix.presentation.home.homeFragmentAdapter.HomeFragmentAdapter
import br.com.zupflix.presentation.home.homefragmentviewmodel.HomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private val viewModel: HomeFragmentViewModel by lazy {
        ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let { fragemntActivity ->
            viewModel.movieLiveData.observe(fragemntActivity, Observer {
                it?.let { movieList ->
                    with(recyclerViewHome) {
                        layoutManager = GridLayoutManager(fragemntActivity, 2)
                        setHasFixedSize(true)
                        adapter = HomeFragmentAdapter(movieList) { movie ->
                            Toast.makeText(
                                fragemntActivity,
                                "Detalhe do filme ${movie.originalTitle}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

            })
        }

        viewModel.getMovie(BuildConfig.API_KEY)

    }

}
