package com.ejemplo.login.io.response.data

import android.os.Parcel
import android.os.Parcelable

data class UserFindResponse(
    val id: Long,
    val nombres: String,
    val apellidos: String,
    val dni: String,
    val historia: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(nombres)
        parcel.writeString(apellidos)
        parcel.writeString(dni)
        parcel.writeString(historia)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserFindResponse> {
        override fun createFromParcel(parcel: Parcel): UserFindResponse {
            return UserFindResponse(parcel)
        }

        override fun newArray(size: Int): Array<UserFindResponse?> {
            return arrayOfNulls(size)
        }
    }
}
