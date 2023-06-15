package uz.softex.qrcodegenerator.companyFiles.controller

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
import uz.softex.qrcodegenerator.companyFiles.dto.CompanyFilesDto
import uz.softex.qrcodegenerator.companyFiles.entity.CompanyFiles
import uz.softex.qrcodegenerator.companyFiles.service.CompanyFileService
import uz.softex.qrcodegenerator.payload.ApiResponse
import uz.softex.qrcodegenerator.payload.ApiResponseGeneric
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("companyFiles")
class CompanyFilesController(val service: CompanyFileService) {
    @PostMapping
    fun add(@RequestBody @Valid dto: CompanyFilesDto): ResponseEntity<ApiResponse> {
        return ResponseEntity.ok(service.add(dto))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<ApiResponse> {
        return ResponseEntity.ok(service.delete(id))
    }

    @GetMapping("/{companyId}")
    fun getAll(@PathVariable companyId: Int): ResponseEntity<ApiResponseGeneric<List<CompanyFiles>>> {
        return ResponseEntity.ok(service.getAllByCompanyId(companyId))
    }

    @PatchMapping
    fun update(@RequestBody dto: CompanyFilesDto): ResponseEntity<ApiResponse> {
        return ResponseEntity.ok(service.update(dto = dto))
    }

    @GetMapping("/qrcodeGenerate")
    fun generateQrCode(
        response: HttpServletResponse,
        @RequestParam companyId: Int,
        @RequestParam width: Int,
        @RequestParam height: Int
    ) {
        service.generateQRCode(response, companyId, width, height)
    }

    @GetMapping("/{companyId}/getImages")
    fun getImages(@PathVariable companyId: Int): HttpEntity<*> {
        return ResponseEntity.ok(service.getImages(companyId))
    }

    @GetMapping("/{companyId}/getFiles")
    fun getFiles(@PathVariable companyId: Int): HttpEntity<*> {
        return ResponseEntity.ok(service.getFiles(companyId))
    }
}