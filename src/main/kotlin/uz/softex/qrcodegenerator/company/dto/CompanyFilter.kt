package uz.softex.qrcodegenerator.company.dto

class CompanyFilter(
    var name: String?,
    var director: String?,
    var phoneNumber: String?,
    var address: String?,
    val sort: MutableMap<String, String>?
) {

}