package extensions


interface ProjectExtension {
    val name: String
    fun configure(extension: Any)

    companion object
}
