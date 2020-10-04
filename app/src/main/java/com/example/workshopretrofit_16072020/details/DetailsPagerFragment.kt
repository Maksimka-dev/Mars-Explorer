package com.example.workshopretrofit_16072020.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.workshopretrofit_16072020.R
import com.example.workshopretrofit_16072020.api.MarsPicture
import kotlinx.android.synthetic.main.details_pager_fragment.*


class DetailsPagerFragment : Fragment() {

    companion object {

        private const val ARGS_MOVIE = "ARGS_MOVIE"
        private const val ARGS_MOVIE_POSITION = "ARGS_MOVIE_POSITION"

        fun newInstance(
            picture: List<MarsPicture>,
            position: Int
        ): DetailsPagerFragment {
            return DetailsPagerFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARGS_MOVIE, ArrayList(picture))
                    putInt(ARGS_MOVIE_POSITION, position)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_pager_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movies = arguments?.getParcelableArrayList<MarsPicture>(ARGS_MOVIE)
            ?: throw IllegalArgumentException("Missing movie argument")
        vp_pager.run {
            adapter = ViewPagerAdapter(childFragmentManager, movies)
            currentItem = arguments?.getInt(ARGS_MOVIE_POSITION) ?: 0
        }

    }
}