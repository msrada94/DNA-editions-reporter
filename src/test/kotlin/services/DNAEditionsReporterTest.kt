package services

import model.DNAEditionsReport
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

class DNAEditionsReporterTest {
    private val testInstance = DNAEditionsReporter

    @Test
    fun `report editions from well-formatted file`() {
        val result = testInstance.readTSVFile(File("seq.tsv"))

        assertThat(result).isEqualTo(
            DNAEditionsReport(
                totalDNAEditionRequestChecked = 8,
                totalDeletions = 2,
                totalInsertions = 1,
                totalMutations = 2
            )
        )
    }
}
