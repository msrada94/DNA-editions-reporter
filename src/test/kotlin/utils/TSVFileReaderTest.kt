package utils

import exceptions.BadFormattedLineException
import exceptions.FileFormatException
import model.DNAEditionCheckRequest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import java.io.FileWriter
import java.nio.file.Files

class TSVFileReaderTest {
    private val tempFile = Files.createTempDirectory("tempFolder").toFile()

    @Test
    fun `read lines from tsv file`() {
        val file = tempFile.resolve("well-formatted.tsv").apply { createNewFile() }
        FileWriter(file).apply {
            appendLine("ID \tOriginal_Seq\tResult")
            appendLine("1\tinitSeq1\tfinalSeq1")
            appendLine("2\tinitSeq2\tfinalSeq2")
            appendLine("3\tinitSeq3\tfinalSeq3")
            close()
        }

        val result = TSVFileReader.getCheckingRequests(file)

        val resultIterator = result.iterator()
        assertThat(resultIterator.next()).isEqualTo(DNAEditionCheckRequest(1, "initSeq1", "finalSeq1"))
        assertThat(resultIterator.next()).isEqualTo(DNAEditionCheckRequest(2, "initSeq2", "finalSeq2"))
        assertThat(resultIterator.next()).isEqualTo(DNAEditionCheckRequest(3, "initSeq3", "finalSeq3"))
    }

    @Test
    fun `read lines from no tsv file`() {
        val file = tempFile.resolve("bad-formatted.xml").apply { createNewFile() }
        FileWriter(file).apply {
            appendLine("ID \tOriginal_Seq\tResult")
            appendLine("1\tinitSeq1\tfinalSeq1")
            appendLine("2\tinitSeq2\tfinalSeq2")
            appendLine("3\tinitSeq3\tfinalSeq3")
            close()
        }

        assertThatExceptionOfType(FileFormatException::class.java)
            .isThrownBy { TSVFileReader.getCheckingRequests(file) }
            .withMessage("the file bad-formatted.xml does not have .tsv format")
    }

    @Test
    fun `read lines of bad titled file`() {
        val file = tempFile.resolve("bad-title.tsv").apply { createNewFile() }
        val badColumnNames = "bad formatted columns names line"
        FileWriter(file).apply {
            appendLine("bad formatted columns names line")
            appendLine("1\tinitSeq1\tfinalSeq1\n")
            close()
        }

        assertThatExceptionOfType(BadFormattedLineException::class.java)
            .isThrownBy { TSVFileReader.getCheckingRequests(file) }
            .withMessage("The line \"$badColumnNames\" is not well-formatted")
    }
}
