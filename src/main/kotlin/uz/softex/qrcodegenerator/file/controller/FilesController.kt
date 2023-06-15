package uz.softex.qrcodegenerator.file.controller

import org.springframework.http.HttpEntity
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import uz.softex.qrcodegenerator.file.serviice.FileService
import uz.softex.qrcodegenerator.payload.ApiResponse
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/files")
class FilesController(val service: FileService) {
    @PostMapping(
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun add(@RequestParam(value = "document", required = true)  file:MultipartFile): HttpEntity<*> {
        return ResponseEntity.ok(service.add(file))
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Int, response: HttpServletResponse): ResponseEntity<Unit> {
        return ResponseEntity.ok(service.getOne(id, response))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<ApiResponse> {
        return ResponseEntity.ok(service.delete(id))
    }

    @PatchMapping(
        value = ["/{id}"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun update(@RequestParam files: MultipartFile, @PathVariable id: Int): ResponseEntity<Unit> {
        return ResponseEntity.ok(service.update(id,files))
    }
}