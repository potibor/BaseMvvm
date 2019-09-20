package com.hsnozan.basemvvm.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginResponseModel(
    @field:SerializedName("homepage")
    val homePage: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("overview")
    val overviewLikes: String? = null,

    @field:SerializedName("original_title")
    val original_title: String? = null,

    @field:SerializedName("popularity")
    val popularity: Double? = null,

    @field:SerializedName("poster_path")
    val poster_path: String? = null
) : Parcelable
