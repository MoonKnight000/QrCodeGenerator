package uz.softex.qrcodegenerator.handler

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import uz.softex.qrcodegenerator.company.exeption.CompanyNotFound
import uz.softex.qrcodegenerator.companyFiles.exeption.CompanyFileNotFound
import uz.softex.qrcodegenerator.file.exception.FileNotFound
import uz.softex.qrcodegenerator.payload.ApiResponse
import java.util.function.Consumer

@ControllerAdvice
class Handler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(CompanyNotFound::class)
    fun handleCompanyNotFound(e: CompanyNotFound): ResponseEntity<ApiResponse> {
        return ResponseEntity.status(400).body(ApiResponse("Company not found", false))
    }

    @ExceptionHandler(FileNotFound::class)
    fun handleFileNotFound(e: FileNotFound): ResponseEntity<ApiResponse> {
        return ResponseEntity.status(400).body(ApiResponse(" File  not found", false))
    }

    @ExceptionHandler(CompanyFileNotFound::class)
    fun handleCompanyFileNotFound(e: CompanyFileNotFound): ResponseEntity<ApiResponse> {
        return ResponseEntity.status(400).body(ApiResponse("Company file not found", false))
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        var message:String=""
        val allErrors = ex.bindingResult.allErrors
        allErrors.forEach(Consumer { i: ObjectError -> message = i.defaultMessage.toString() })
        return handleExceptionInternal(
            ex, message,
            HttpHeaders(), HttpStatus.BAD_REQUEST, request
        )
    }
}