package uz.softex.qrcodegenerator.companyFiles.dto

import uz.softex.qrcodegenerator.companyFiles.entity.FileType
import javax.validation.constraints.NotNull

class CompanyFilesDto {
    var id: Int? = null

    @field:NotNull(message = "file is null")
    var fileId: Int? =null

    @field:NotNull(message = "company is null")
    var companyId: Int? =null

    @field:NotNull(message = "file type is null")
    var fileType: FileType? = FileType.LOGO
}