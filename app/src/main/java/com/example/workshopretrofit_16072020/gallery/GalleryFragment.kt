package com.example.workshopretrofit_16072020.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import coil.api.load
import com.example.workshopretrofit_16072020.R
import com.example.workshopretrofit_16072020.api.MarsPicture
import kotlinx.android.synthetic.main.gallery_fragment.*


class GalleryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.gallery_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.arguments?.getParcelable<MarsPicture>(ARGS_CAT).let {
            gallery_date?.text = it?.date
            gallery_camera_name?.text = it?.camera?.name
            gallery_picture_name.text = it?.id.toString()
            bigGalleryPicture.load(it?.imageUrl)
        }
        performAnimation(bigGalleryPicture)
    }

    private fun performAnimation(myView: View) {
        myView.animate()
            .withLayer()
            .rotationY(90f)
            .setDuration(300)
            .withEndAction {
                myView.rotationY = -90f
                myView.animate()
                    .withLayer()
                    .rotationY(0f)
                    .setDuration(300)
                    .withEndAction {
                        //nextStep() //or nothing
                    }
                    .start()
            }
            .start()
        myView.cameraDistance = 8000f
    }

    companion object {
        private const val ARGS_CAT = "ARGS_CAT"
        fun newInstance(cat: MarsPicture): GalleryFragment {
            return GalleryFragment().apply {
                arguments = bundleOf(ARGS_CAT to cat)
            }
        }
    }
}