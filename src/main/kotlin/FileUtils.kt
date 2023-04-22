import java.io.File

object FileUtils {

    fun readLines(path: String): List<CheckingEditionRequest> {
        return File(path).bufferedReader().lineSequence().drop(1).map { line ->
            val (id, dnaSequence, possibleEdition) = line.split("\t")
            CheckingEditionRequest(id.toInt(), dnaSequence, possibleEdition)
        }.toList()
    }
}
