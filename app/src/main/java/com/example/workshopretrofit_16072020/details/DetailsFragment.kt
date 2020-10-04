package com.example.workshopretrofit_16072020.details

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import coil.api.load
import com.example.workshopretrofit_16072020.R
import com.example.workshopretrofit_16072020.api.Camera
import com.example.workshopretrofit_16072020.api.MarsPicture
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.details_fragment.*


class DetailsFragment : Fragment() {

    private var id: String? = null
    private var imageUrl: String? = null
    private var date: String? = null
    private var sol: String? = null
    private var camera: Camera? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.arguments?.getParcelable<MarsPicture>(ARGS_CAT).let {
            id = it?.id.toString()
            imageUrl = it?.imageUrl
            date = it?.date
            camera = it?.camera
            sol = it?.sol.toString()
            textOverview?.text = it?.date
            bigPicture.load(it?.imageUrl)
        }

        fab.setOnClickListener {
            if (isSavePermissionsGranted()) {
                val uri = saveImageInGallery()
                Toast.makeText(context, "${getString(R.string.saved)}$uri", Toast.LENGTH_SHORT)
                    .show()
            } else {
                requestSavePermission()
            }
        }

        fab2.setOnClickListener {
            Firebase.firestore.collection("pictures").add(
                hashMapOf(
                    "id" to id,
                    "imageUrl" to imageUrl,
                    "date" to date,
                    "camera" to camera?.name,
                    "sol" to sol
                )
            )
                .addOnSuccessListener {
                    Toast.makeText(
                        context,
                        getString(R.string.save_to_firestore),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener {
                    Toast.makeText(
                        context,
                        getString(R.string.error_save_to_firestore),
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

    private fun isSavePermissionsGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            context as Activity,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) ==
                PackageManager.PERMISSION_GRANTED
    }

    private fun requestSavePermission() {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            REQUEST_CODE
        )
    }

    @SuppressLint("MissingPermission")
    private fun saveImageInGallery(): Uri {
        val drawable = bigPicture.drawable
        val bitmap = (drawable as BitmapDrawable).bitmap
        val savedImageURL = MediaStore.Images.Media.insertImage(
            context?.contentResolver,
            bitmap,
            URL_TITLE,
            URL_DESC
        )
        return Uri.parse(savedImageURL)
    }

    companion object {
        private const val REQUEST_CODE = 100
        private const val ARGS_CAT = "ARGS_CAT"
        private const val URL_TITLE = "ChartalFeature"
        private const val URL_DESC = "My Image"

        fun newInstance(cat: MarsPicture): DetailsFragment {
            return DetailsFragment().apply {
                arguments = bundleOf(ARGS_CAT to cat)
            }
        }
    }
}