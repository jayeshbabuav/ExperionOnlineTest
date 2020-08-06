package `in`.coding.experiononlinetest.models

import com.google.gson.annotations.SerializedName

data class ItemModel(

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("imageHref")
    var imageHref: String? = null

)