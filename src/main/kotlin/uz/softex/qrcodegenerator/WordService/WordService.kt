package uz.softex.qrcodegenerator.WordService

import org.apache.poi.util.Units
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

@Service
class WordService {
    private var document: XWPFDocument = XWPFDocument()
    private var paragraph = document.createParagraph()
    private var run = paragraph.createRun()
    fun setImage(path: String) {
        run.addPicture(
            FileInputStream(path),
            XWPFDocument.PICTURE_TYPE_JPEG,
            path,
            Units.toEMU(200.0),
            Units.toEMU(200.0)
        )
    }

    fun addParagraph() {
        paragraph = document.createParagraph()
        run = paragraph.createRun()
    }

    fun setText(text: String) {
        run.setText(text)
        run.addBreak()
    }

    fun writeToFile(path: String = "D:\\exportsToWeb/first.docx") {
        val outputStream = FileOutputStream(File(path))
        document.write(outputStream)
        outputStream.close()
    }

    fun create() {
        document = XWPFDocument()
    }
}