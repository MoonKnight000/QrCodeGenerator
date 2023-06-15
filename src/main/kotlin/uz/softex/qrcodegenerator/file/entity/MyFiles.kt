package uz.softex.qrcodegenerator.file.entity

import javax.persistence.*

@Entity
@Table(
    uniqueConstraints = [
        UniqueConstraint(name = "UniqueNameAndPath", columnNames = ["name", "path"])
    ]
)
class MyFiles(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,
    var path: String,
    var type: String,
    var name: String
) {
    constructor() : this(null, "", "", "")
}