package `in`.coding.experiononlinetest.models

import com.google.gson.annotations.SerializedName

data class MainResponseModel(

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("rows")
    var rows: List<ItemModel>? = null

)