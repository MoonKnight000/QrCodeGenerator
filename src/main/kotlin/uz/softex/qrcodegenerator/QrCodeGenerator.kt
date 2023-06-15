package uz.softex.qrcodegenerator

import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageConfig
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import java.io.ByteArrayOutputStream
import java.nio.file.Paths

fun main() {
    generateImageQRCode("assalomy alaykum", 500,500)
}

fun generateImageQRCode(text: String, width: Int, height: Int) {
    val qrCode: QRCodeWriter = QRCodeWriter()
    val encode = qrCode.encode(text, BarcodeFormat.QR_CODE, width, height)
    MatrixToImageWriter.writeToPath(encode, "JPG", Paths.get("D:\\fayllar/9279612d5-d40b-4834-9390-806647469426.JPG"))
}

fun generateByteQrCode(text: String, width: Int, height: Int, path: String): ByteArray {
    var outputStream: ByteArrayOutputStream? = null
    val qrcode: QRCodeWriter = QRCodeWriter()
    val encode = qrcode.encode(text, BarcodeFormat.QR_CODE, width, height)
    outputStream = ByteArrayOutputStream()
    val config = MatrixToImageConfig(0xFF000000.toInt(), 0xFFFFFFFF.toInt())
    MatrixToImageWriter.writeToStream(encode, "PNG", outputStream, config)
    return outputStream.toByteArray()
}