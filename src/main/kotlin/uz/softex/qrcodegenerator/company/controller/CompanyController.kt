package uz.softex.qrcodegenerator.company.controller

import org.springframework.data.domain.Page
import org.springframework.http.HttpEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import uz.softex.qrcodegenerator.company.dto.CompanyFilter
import uz.softex.qrcodegenerator.company.entity.Company
import uz.softex.qrcodegenerator.company.projection.CompanyProjection
import uz.softex.qrcodegenerator.company.service.CompanyService
import uz.softex.qrcodegenerator.payload.ApiResponseGeneric

@RestController
@RequestMapping("/company")
class CompanyController(val service: CompanyService) {
    @GetMapping
    fun getAll(
        @RequestParam(defaultValue = "10") size: Int,
        @RequestParam(defaultValue = "0") page: Int
    ): ResponseEntity<Any> {
        return ResponseEntity.ok(service.getAll(page, size))
    }

    @GetMapping("/{id}")
    fun getOne(
        @PathVariable id: Int
    ): ResponseEntity<Any> {
        return ResponseEntity.ok(service.getById(id))
    }

    @PostMapping
    fun add(@RequestBody company: Company): ResponseEntity<Any> {
        return ResponseEntity.ok(service.add(company))
    }

    @PatchMapping
    fun update(@RequestBody company: Company): ResponseEntity<Any> {
        return ResponseEntity.ok(service.update(company))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): HttpEntity<*> {
        return ResponseEntity.ok(service.delete(id))
    }

    @PostMapping("/filter")
    fun filter(@RequestBody filter: CompanyFilter,@RequestParam page:Int, @RequestParam size:Int): ResponseEntity<ApiResponseGeneric<Page<CompanyProjection>>> {
        return ResponseEntity.ok(service.filter(filter,page, size))
    }
}