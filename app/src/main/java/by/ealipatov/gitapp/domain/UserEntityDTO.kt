package by.ealipatov.gitapp.domain

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserEntityDTO(
    @SerializedName("login")
    @Expose
    val login: String,

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("avatar_url")
    @Expose
    val avatarUrl: String
) : Parcelable