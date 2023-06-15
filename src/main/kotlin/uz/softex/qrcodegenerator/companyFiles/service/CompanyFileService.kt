package uz.softex.qrcodegenerator.companyFiles.service

import org.springframework.stereotype.Service
import org.springframework.util.FileCopyUtils
import uz.softex.qrcodegenerator.WordService.WordService
import uz.softex.qrcodegenerator.company.exeption.CompanyNotFound
import uz.softex.qrcodegenerator.company.repository.CompanyRepository
import uz.softex.qrcodegenerator.companyFiles.dto.CompanyFilesDto
import uz.softex.qrcodegenerator.companyFiles.entity.CompanyFiles
import uz.softex.qrcodegenerator.companyFiles.exeption.CompanyFileNotFound
import uz.softex.qrcodegenerator.companyFiles.repository.CompanyFilesRepository
import uz.softex.qrcodegenerator.file.exception.FileNotFound
import uz.softex.qrcodegenerator.file.repository.FileRepository
import uz.softex.qrcodegenerator.payload.ApiResponse
import uz.softex.qrcodegenerator.payload.ApiResponseGeneric
import uz.softex.qrcodegenerator.qrCodeService.QrCodeService
import java.io.FileInputStream
import javax.servlet.http.HttpServletResponse

@Service
class CompanyFileService(
    private val repository: CompanyFilesRepository,
    private val fileRepository: FileRepository,
    private val companyRepository: CompanyRepository,
    private val qrCode: QrCodeService,
    private val wordService: WordService
) {
    fun add(dto: CompanyFilesDto): ApiResponse {
        val file = fileRepository.findById(dto.fileId!!).orElseThrow { throw FileNotFound() }
        val company = companyRepository.findById(dto.companyId!!).orElseThrow { throw CompanyNotFound() }
        val companyFiles = CompanyFiles(null, file, company, dto.fileType!!)
        repository.save(companyFiles)
        return ApiResponse()
    }

    fun getAllByCompanyId(companyId: Int): ApiResponseGeneric<List<CompanyFiles>> {
        companyRepository.findById(companyId).orElseThrow { throw CompanyNotFound() }
        return ApiResponseGeneric(repository.findAllByCompanyId(companyId))
    }

    fun update(dto: CompanyFilesDto): ApiResponse {
        val findById = repository.findById(dto.id!!).orElseThrow { throw CompanyFileNotFound() }
        findById.file = fileRepository.findById(dto.fileId!!).orElseThrow { throw FileNotFound() }
        findById.company = companyRepository.findById(dto.companyId!!).orElseThrow { throw CompanyNotFound() }
        findById.fileType = dto.fileType!!
        repository.save(findById)
        return ApiResponse()
    }

    fun delete(id: Int): ApiResponse {
        if (!repository.existsById(id)) throw CompanyFileNotFound()
        repository.deleteById(id)
        return ApiResponse()
    }

    fun generateQRCode(response: HttpServletResponse, companyId: Int, width: Int, height: Int) {
        if (!repository.existsById(companyId)) throw CompanyFileNotFound()
        if (getImages(companyId).size != 0) {
            qrCode.generateImageQRCode(getImages(companyId).joinToString(), width, height)
            wordService.setText("This is the location of the image")
            wordService.setImage("D:\\fayllar/927963d5-d40b-4834-9390-806647469426.JPG")
            wordService.addParagraph()
        }
        if (getFiles(companyId).size != 0) {
            qrCode.generateImageQRCode(getFiles(companyId).joinToString(), width, height)
            wordService.setText("This is the location of the file")
            wordService.setImage("D:\\fayllar/927963d5-d40b-4834-9390-806647469426.JPG")
        }
        wordService.writeToFile()
        wordService.create()

        response.setHeader("Content-Disposition", "attachment; filename=\"first.docx\"");
        response.contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
        FileCopyUtils.copy(FileInputStream("D:\\exportsToWeb/first.docx"), response.outputStream)
    }

    fun getImages(id: Int): MutableList<String> {
        val findAll = repository.findAllByCompanyId(id)
        val url = mutableListOf<String>()
        findAll.forEach {
            if (it.file.type.contains("image"))
                url.add("http://192.168.0.137:8080/files/${it.file.id}")
        }
        return url
    }

    fun getFiles(id: Int): MutableList<String> {
        val findAll = repository.findAllByCompanyId(id)
        val url = mutableListOf<String>()
        findAll.forEach {
            if (!it.file.type.contains("image"))
                url.add("http://192.168.0.137:8080/files/${it.file.id}")

        }
        return url
    }
}