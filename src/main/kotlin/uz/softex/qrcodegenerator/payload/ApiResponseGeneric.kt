package uz.softex.qrcodegenerator.payload

class ApiResponseGeneric<T>(
    var message: String = "successfully",
    var success: Boolean = true,
    val data: T
) {
    constructor(data: T) : this("successfully", true, data)
}