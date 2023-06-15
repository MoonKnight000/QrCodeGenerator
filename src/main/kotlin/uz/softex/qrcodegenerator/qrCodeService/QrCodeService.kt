package uz.softex.qrcodegenerator.qrCodeService

import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import org.springframework.stereotype.Service
import org.springframework.util.FileCopyUtils
import uz.softex.qrcodegenerator.file.exception.FileNotFound
import java.io.FileInputStream
import java.nio.file.Paths
import javax.servlet.http.HttpServletResponse

@Service
class QrCodeService {
    private val path = "D:\\fayllar/927963d5-d40b-4834-9390-806647469426.JPG"
    fun generateImageQRCode(text: String, width: Int, height: Int) {
        val qrCode = QRCodeWriter()
        val encode = qrCode.encode(text, BarcodeFormat.QR_CODE, width, height)
        MatrixToImageWriter.writeToPath(
            encode,
            "JPG",
            Paths.get(path)
        )
    }

//    fun getQrCode(text: String, width: Int, height: Int) {
//        generateImageQRCode(text, width, height)
//        response.setHeader("Content-Disposition", "attachment; filename=\"927963d5-d40b-4834-9390-806647469426.JPG\"");
//        response.contentType = "JPG"
//        val inputStream = FileInputStream(path)
//        FileCopyUtils.copy(inputStream, response.outputStream)
//    }
}