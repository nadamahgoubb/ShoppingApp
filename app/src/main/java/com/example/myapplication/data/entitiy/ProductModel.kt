package com.example.myapplication.data.entitiy

import android.os.Parcel
import android.os.Parcelable

data class ProductModel(
    val `data`: Data,
    val message: Any,
    val status: Boolean
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Data::class.java.classLoader)!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<ProductModel> {
        override fun createFromParcel(parcel: Parcel): ProductModel {
            return ProductModel(parcel)
        }

        override fun newArray(size: Int): Array<ProductModel?> {
            return arrayOfNulls(size)
        }
    }
}


data class Data(
    val current_page: Int,
    val `data`: List<DataX>,
    val first_page_url: String,
    val from: Int,
    val last_page: Int,
    val last_page_url: String,
    val next_page_url: Any,
    val path: String,
    val per_page: Int,
    val prev_page_url: Any,
    val to: Int,
    val total: Int
)

data class DataX(
    val id: Int,
    val description: String?,
    val discount: Int?,
    val image: String?,
    val images: List<String>?,
    val in_cart: Boolean?,
    val in_favorites: Boolean?,
    val name: String?,
    val old_price: Float?,
    val price: Float?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readFloat()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(description)
        parcel.writeInt(discount!!)
        parcel.writeString(image)
        parcel.writeStringList(images)
        parcel.writeByte(if (in_cart!!) 1 else 0)
        parcel.writeByte(if (in_favorites!!) 1 else 0)
        parcel.writeString(name)
        parcel.writeFloat(old_price!!)
        parcel.writeFloat(price!!)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductModel> {
        override fun createFromParcel(parcel: Parcel): ProductModel {
            return ProductModel(parcel)
        }

        override fun newArray(size: Int): Array<ProductModel?> {
            return arrayOfNulls(size)
        }
    }
}
