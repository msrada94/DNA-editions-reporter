package utils

import exceptions.BadFormattedLineException
import exceptions.FileFormatException
import model.DNAEditionCheckRequest
import java.io.File

object TSVFileReader {
    fun getCheckingRequests(file: File): Sequence<DNAEditionCheckRequest> {
        checkTSVFormat(file)
        val firstLine = file.useLines { it.first() }
        checkColumnsNames(firstLine)

        return file.bufferedReader().lineSequence().drop(1).map { line -> lineToEditionCheckRequest(line) }
    }

    private fun checkColumnsNames(line: String) {
        require(line == columnsNames) { throw BadFormattedLineException(line) }
    }

    private fun lineToEditionCheckRequest(line: String): DNAEditionCheckRequest {
        val (id, dnaSequence, possibleEdition) = line.split("\t")
        return DNAEditionCheckRequest(id.toInt(), dnaSequence, possibleEdition)
    }

    private fun checkTSVFormat(file: File) {
        require(file.name.endsWith(".tsv")) { throw FileFormatException(file.name, ".tsv") }
    }

    private const val columnsNames = "ID \tOriginal_Seq\tResult"
}
