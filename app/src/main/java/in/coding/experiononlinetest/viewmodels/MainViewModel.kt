package `in`.coding.experiononlinetest.viewmodels

import `in`.coding.experiononlinetest.models.MainResponseModel
import `in`.coding.experiononlinetest.repositories.MainRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private var data: MutableLiveData<MainResponseModel>? = null

    fun getList(): LiveData<MainResponseModel>? {
        if (data == null)
            data = MainRepository.getList()
        return data
    }

}