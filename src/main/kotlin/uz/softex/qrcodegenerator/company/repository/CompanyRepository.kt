package uz.softex.qrcodegenerator.company.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import uz.softex.qrcodegenerator.company.entity.Company
import uz.softex.qrcodegenerator.company.projection.CompanyProjection

interface CompanyRepository : JpaRepository<Company, Int> {
    @Query(
        nativeQuery = true,
        value = "select com.id, com.name, com.phone_number, com.address, com.director from company com where " +
                "search_word(name,:name) and search_word(director,:director) " +
                "and search_word(phone_number , :phoneNumber) and search_word(address,:address)"
    )
    fun filter(
        name: String,
        director: String,
        phoneNumber: String,
        address: String,
        pageable: Pageable
    ): Page<CompanyProjection>
}