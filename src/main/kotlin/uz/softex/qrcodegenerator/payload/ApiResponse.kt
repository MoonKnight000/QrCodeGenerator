package uz.softex.qrcodegenerator.payload

data class ApiResponse(
    var message: String = "successfully",
    var success: Boolean = true
) {
    constructor() : this("successfully",true)
}