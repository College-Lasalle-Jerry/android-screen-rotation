package com.example.screenrotation07390

import android.os.Parcel
import android.os.Parcelable

data class Course(
    val courseNo: String,
    val courseName: String,
    val courseDuration: Int
) : Parcelable { // parcable will convert all of the information into stream of data (Bytes)
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(courseNo)
        parcel.writeString(courseName)
        parcel.writeInt(courseDuration)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Course> {
        override fun createFromParcel(parcel: Parcel): Course {
            return Course(parcel)
        }

        override fun newArray(size: Int): Array<Course?> {
            return arrayOfNulls(size)
        }
    }
}