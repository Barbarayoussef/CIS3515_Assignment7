package edu.temple.browsr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.net.URL

class WebViewModel:ViewModel() {
    private val Url=MutableLiveData<String>()

    fun get():LiveData<String>{
        return Url
    }
    fun updateUrl(newUrl: String) {
        Url.value = newUrl
    }

}