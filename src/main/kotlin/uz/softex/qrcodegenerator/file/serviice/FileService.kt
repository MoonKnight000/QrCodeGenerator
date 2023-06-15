package uz.softex.qrcodegenerator.file.serviice

import org.springframework.stereotype.Service
import org.springframework.util.FileCopyUtils
import org.springframework.web.multipart.MultipartFile
import uz.softex.qrcodegenerator.company.exeption.CompanyNotFound
import uz.softex.qrcodegenerator.company.repository.CompanyRepository
import uz.softex.qrcodegenerator.file.entity.MyFiles
import uz.softex.qrcodegenerator.file.exception.FileNotFound
import uz.softex.qrcodegenerator.file.repository.FileRepository
import uz.softex.qrcodegenerator.payload.ApiResponse
import java.io.File
import java.io.FileInputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import javax.servlet.http.HttpServletResponse

@Service
class FileService(val repository: FileRepository) {
    fun add(file: MultipartFile) {
//        file.transferTo(File("F:\\qrCodeGeneratorFiles/"))
        val path: Path = Paths.get("D:\\fayllar/${file.originalFilename}")
        try {
            Files.copy(
                file.inputStream,
                path
            )
            var myFiles = MyFiles()
            myFiles.name = file.originalFilename.toString()
            myFiles.path = "D:\\fayllar/${file.originalFilename}"
            myFiles.type = file.contentType.toString()
            repository.save(myFiles)
        } catch (_: Exception) {
        }
    }

    fun delete(id: Int): ApiResponse {
        val orElseThrow = repository.findById(id).orElseThrow { throw FileNotFound() }
        File(orElseThrow.path).delete()
        repository.delete(orElseThrow)
        return ApiResponse()
    }

    fun update(id: Int, part: MultipartFile) {
        val file = repository.findById(id).orElseThrow { throw FileNotFound() }
        File(file.path).delete()
        try {
            val path: Path = Paths.get("D:\\fayllar/${part.originalFilename}")
            Files.copy(
                part.inputStream,
                path
            )
            file.name = part.originalFilename.toString()
            file.path = "D:\\fayllar/${part.originalFilename}"
            file.type = part.contentType.toString()
            repository.save(file)
        } catch (_: Exception) {
        }
    }

    fun getOne(id: Int, response: HttpServletResponse) {
        val file = repository.findById(id).orElseThrow { throw FileNotFound() }
        response.setHeader("Content-Disposition", "attachment; filename=\"${file.name}\"");
        response.contentType = file.type
        val inputStream = FileInputStream(file.path)
        FileCopyUtils.copy(inputStream, response.outputStream)
    }
}