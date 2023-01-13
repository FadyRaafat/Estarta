package com.fady.estarta.data.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductsResponse(
    @SerializedName("results")
    var results: List<Result?>?
)

data class Result(
    @SerializedName("created_at") var createdAt: String?,
    @SerializedName("image_ids") var imageIds: List<String?>?,
    @SerializedName("image_urls") var imageUrls: List<String?>?,
    @SerializedName("image_urls_thumbnails") var imageUrlsThumbnails: List<String?>?,
    @SerializedName("name") var name: String?,
    @SerializedName("price") var price: String?,
    @SerializedName("uid") var uid: String?
) : Serializable
