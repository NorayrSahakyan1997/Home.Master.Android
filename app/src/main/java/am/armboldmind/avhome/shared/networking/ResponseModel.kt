package am.armboldmind.avhome.shared.networking

import android.os.Parcel
import android.os.Parcelable

class ResponseModel<T>() : Parcelable {
    private var success: Boolean =false
    private var data: T? = null
    private var message: String? = null

    constructor(parcel: Parcel) : this() {
        success = parcel.readValue(Boolean::class.java.classLoader) as Boolean
        message = parcel.readString()
    }

    fun getSuccess(): Boolean {
        return success
    }

    fun setSuccess(success: Boolean) {
        this.success = success
    }

    fun getData(): T? {
        return data
    }

    fun setData(data: T) {
        this.data = data
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String) {
        this.message = message
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(success)
        parcel.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResponseModel<Parcel>> {
        override fun createFromParcel(parcel: Parcel): ResponseModel<Parcel> {
            return ResponseModel(parcel)
        }

        override fun newArray(size: Int): Array<ResponseModel<Parcel>?> {
            return arrayOfNulls(size)
        }
    }
}