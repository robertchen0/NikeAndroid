package com.example.nikeurbandictionary.model.classes

import android.os.Parcel
import android.os.Parcelable


data class Definition (
    val list: List<ListDefinitions>
) : Parcelable {
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeList(list)
    }

    override fun describeContents(): Int {
        return 0
    }
}
