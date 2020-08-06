package `in`.coding.experiononlinetest.repositories

import `in`.coding.experiononlinetest.configurations.RetrofitConfig
import `in`.coding.experiononlinetest.models.MainResponseModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainRepository {

    fun getList() : MutableLiveData<MainResponseModel> {
        var data: MutableLiveData<MainResponseModel> = MutableLiveData()

        var call = RetrofitConfig.apiInterface.getList()
        call.enqueue(object : Callback<MainResponseModel> {
            override fun onFailure(call: Call<MainResponseModel>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<MainResponseModel>, response: Response<MainResponseModel>) {
                data.value = response.body()
            }
        })
        return data
    }

}