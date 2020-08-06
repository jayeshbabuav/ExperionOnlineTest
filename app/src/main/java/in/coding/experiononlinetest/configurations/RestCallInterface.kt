package `in`.coding.experiononlinetest.configurations

import `in`.coding.experiononlinetest.models.MainResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface RestCallInterface {

    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getList(): Call<MainResponseModel>

}