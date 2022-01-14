package com.expert.foodbd

import com.google.gson.annotations.SerializedName

data class GroupModel(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("price") var price: Int? = null,
    @SerializedName("category") var category: String? = null

)