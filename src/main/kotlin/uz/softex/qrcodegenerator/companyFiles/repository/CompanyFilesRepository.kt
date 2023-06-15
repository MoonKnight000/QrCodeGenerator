package uz.softex.qrcodegenerator.companyFiles.repository

import org.springframework.data.jpa.repository.JpaRepository
import uz.softex.qrcodegenerator.companyFiles.entity.CompanyFiles

interface CompanyFilesRepository : JpaRepository<CompanyFiles, Int> {
    fun findAllByCompanyId(companyId: Int): List<CompanyFiles>
}