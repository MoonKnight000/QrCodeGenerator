package uz.softex.qrcodegenerator.company.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import uz.softex.qrcodegenerator.company.entity.Company
import uz.softex.qrcodegenerator.company.exeption.CompanyNotFound
import uz.softex.qrcodegenerator.payload.ApiResponseGeneric
import uz.softex.qrcodegenerator.company.repository.CompanyRepository
import uz.softex.qrcodegenerator.payload.ApiResponse

@Service
class CompanyService(private val repository: CompanyRepository) {
    fun getAll(page: Int, size: Int): ApiResponseGeneric<Page<Company>> {
        val pageable: Pageable = PageRequest.of(page, size)
        val findAll = repository.findAll(pageable)
        return ApiResponseGeneric(findAll)
    }

    fun getById(id: Int): ApiResponseGeneric<Company> {
        return ApiResponseGeneric(repository.findById(id).orElseThrow { throw CompanyNotFound() })
    }

    fun add(company: Company): ApiResponse {
        company.id = null
        repository.save(company)
        return ApiResponse()
    }

    fun update(company: Company) {
        if (company.id == null || !repository.existsById(company.id!!))
            throw CompanyNotFound()
        repository.save(company)
    }

    fun delete(id: Int): ApiResponse {
        if(!repository.existsById(id))
            throw CompanyNotFound()
        repository.deleteById(id)
        return ApiResponse()
    }
}