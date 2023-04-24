package services

import org.junit.jupiter.api.Test
import java.io.File

class DNAEditionsReporterTest {
    private val testInstance = DNAEditionsReporter

    @Test
    fun `report editions from well-formatted file`() {
        val result = testInstance.readTSVFile(File("seq.tsv"))

        println(result)
    }
}
