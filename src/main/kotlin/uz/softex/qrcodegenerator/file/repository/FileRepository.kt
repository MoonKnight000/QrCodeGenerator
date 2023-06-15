package uz.softex.qrcodegenerator.file.repository

import org.springframework.data.jpa.repository.JpaRepository
import uz.softex.qrcodegenerator.file.entity.MyFiles

interface FileRepository:JpaRepository<MyFiles,Int> {
}