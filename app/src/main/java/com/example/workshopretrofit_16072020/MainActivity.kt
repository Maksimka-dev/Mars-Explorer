package com.example.workshopretrofit_16072020

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.preference.PreferenceManager
import com.example.workshopretrofit_16072020.adapters.PictureAdapter
import com.example.workshopretrofit_16072020.api.MarsPicture
import com.example.workshopretrofit_16072020.details.DetailsPagerFragment
import com.example.workshopretrofit_16072020.gallery.GalleryActivity
import com.example.workshopretrofit_16072020.settings.SettingsActivity
import com.example.workshopretrofit_16072020.utils.DateConverter
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), PictureAdapter.Listener {

    private val pictureAdapter = PictureAdapter(this)
    private val filmViewModel by viewModels<MainViewModel>()
    private val themeAppMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var themeApp: LiveData<Boolean> = themeAppMutableLiveData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        themeApp.observe(this, Observer { it ->
            if (it == true) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        })
        setContentView(R.layout.activity_main)
        recyclerView.adapter = pictureAdapter
        initProgressBar()
        initErrorHandler()
        loadPictures()
    }

    override fun onResume() {
        super.onResume()
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        themeAppMutableLiveData.value =
            prefs.getString("theme_style", "")?.contains(getString(R.string.dark_theme))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        menuInflater.inflate(R.menu.menu_search, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_open_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                return true
            }
            R.id.action_open_gallery -> {
                startActivity(Intent(this, GalleryActivity::class.java))
                return true
            }
            R.id.action_open_calendar -> {
                val builder = MaterialDatePicker.Builder.datePicker()
                val now = Calendar.getInstance()
                val picker = builder.build()
                picker.show(supportFragmentManager, picker.toString())
                picker.addOnPositiveButtonClickListener {
                    text_inform.isVisible = false
                    mars_imageView.isVisible = false
                    pictureAdapter.clearList()
                    filmViewModel.addListOfPictureForDate(DateConverter().convertDate(it))
                }
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun oItemClicked(position: Int) {
        showDetailsFragment(pictureAdapter.items, position)
    }

    override fun onListScrolled(date: String) {
        filmViewModel.addListOfPictureForDate(DateConverter().addOneDay(date))
    }

    private fun loadPictures() {
        filmViewModel.items.observe(this, Observer { pictures ->
            pictures ?: return@Observer
            pictureAdapter.addItems(pictures)
            text_inform.isVisible = pictures.isEmpty()
            mars_imageView.isVisible = pictures.isEmpty()
        })
    }

    private fun initProgressBar() {
        filmViewModel.isProgressBarVisible.observe(this, Observer { isVisible ->
            picturesLoadProgressBar.isVisible = isVisible
        })
    }

    private fun initErrorHandler() {
        filmViewModel.error.observe(this, Observer { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        })
    }

    private fun showDetailsFragment(
        pictures: List<MarsPicture>,
        position: Int
    ) {
        val detailsFragment = DetailsPagerFragment.newInstance(pictures, position)
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(android.R.id.content, detailsFragment)
            .commit()
    }

}