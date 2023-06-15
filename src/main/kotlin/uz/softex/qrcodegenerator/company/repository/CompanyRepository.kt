package uz.softex.qrcodegenerator.company.repository

import org.springframework.data.jpa.repository.JpaRepository
import uz.softex.qrcodegenerator.company.entity.Company

interface CompanyRepository:JpaRepository<Company,Int> {
}