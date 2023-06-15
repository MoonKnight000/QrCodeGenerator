package uz.softex.qrcodegenerator.companyFiles.entity

import uz.softex.qrcodegenerator.company.entity.Company
import uz.softex.qrcodegenerator.file.entity.MyFiles
import javax.persistence.*

@Entity
class CompanyFiles(
    @Id @GeneratedValue var id: Int?,
    @ManyToOne
    var file:MyFiles,
    @ManyToOne
    var company:Company,
    @Enumerated(value = EnumType.STRING)
    var fileType: FileType
) {
}