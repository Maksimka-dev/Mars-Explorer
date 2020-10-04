package com.example.workshopretrofit_16072020.gallery

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.workshopretrofit_16072020.R
import com.example.workshopretrofit_16072020.adapters.GalleryAdapter
import com.example.workshopretrofit_16072020.api.Camera
import com.example.workshopretrofit_16072020.api.MarsPicture
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : AppCompatActivity(), GalleryAdapter.Listener {

    private val galleryAdapter = GalleryAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        galleryRecyclerView.adapter = galleryAdapter
        loadGalleryOfPictures()
    }

    override fun oItemClicked(picture: MarsPicture) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(android.R.id.content, GalleryFragment.newInstance(picture))
            .commit()
    }

    private fun loadGalleryOfPictures() {
        val marsPictures = mutableListOf<MarsPicture>()
        Firebase.firestore.collection("pictures")
            .get()
            .addOnSuccessListener { querySnapshot ->
                querySnapshot.documents.map {
                    val id = it.getString("id")?.toInt()
                    val imageUrl = it.getString("imageUrl")
                    val date = it.getString("date")
                    val sol = it.getString("sol")?.toInt()
                    val camera = Camera(it.getString("camera"))
                    marsPictures.add(MarsPicture(id, imageUrl, date, sol, camera))
                }
                galleryAdapter.addItems(marsPictures)
            }
            .addOnFailureListener {
                Toast.makeText(
                    this,
                    getString(R.string.error_load_from_firestore),
                    Toast.LENGTH_SHORT
                ).show()
            }

    }

}