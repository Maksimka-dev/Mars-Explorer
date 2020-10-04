package com.example.workshopretrofit_16072020


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workshopretrofit_16072020.api.MarsPicture
import com.example.workshopretrofit_16072020.repository.PicturesRepository
import kotlinx.coroutines.launch

private const val ERROR_LOAD_PICTURES = "Error load pictures"

class MainViewModel : ViewModel() {

    private val _items = MutableLiveData<List<MarsPicture>>()
    private val isProgressBarVisibleMutableLiveData = MutableLiveData<Boolean>()
    private val errorMutableLiveData = MutableLiveData<String>()
    val items: LiveData<List<MarsPicture>> get() = _items
    val isProgressBarVisible: LiveData<Boolean> = isProgressBarVisibleMutableLiveData
    val error: LiveData<String> = errorMutableLiveData

    init {
        _items.value = PicturesRepository.getDefaultPicturesList()
    }

    fun addListOfPictureForDate(date: String) {
        viewModelScope.launch {
            try {
                isProgressBarVisibleMutableLiveData.value = true
                _items.value = PicturesRepository.getListOfPictures(date)
            } catch (e: Throwable) {
               errorMutableLiveData.value = ERROR_LOAD_PICTURES
            } finally {
                isProgressBarVisibleMutableLiveData.value = false
            }
        }
    }
}