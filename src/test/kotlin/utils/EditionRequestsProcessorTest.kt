package utils

import model.DNAEditionCheckRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EditionRequestsProcessorTest {
    private val testInstance = EditionRequestsProcessor

    @Test
    fun `process two no edition one no_edition requests`() {
        val sequence = sequenceOf(
            DNAEditionCheckRequest(1, "AAATTTGGG", "AAATTTGGG"),
            DNAEditionCheckRequest(2, "CCCAAATTT", "CCCAAATTT")
        )

        val result = testInstance.process(sequence)

        assertThat(result.totalDNAEditionRequestChecked).isEqualTo(2)
        assertThat(result.totalDeletions).isEqualTo(0)
        assertThat(result.totalInsertions).isEqualTo(0)
        assertThat(result.totalMutations).isEqualTo(0)
    }

    @Test
    fun `process two deletions one no_edition  requests`() {
        val sequence = sequenceOf(
            DNAEditionCheckRequest(1, "TCATATCACTGA", "TCATATCTGA"),
            DNAEditionCheckRequest(2, "AAATTCTTAAA", "AAATTTTAAA"),
            DNAEditionCheckRequest(3, "AAATTTGGG", "AAATTTGGG")
        )

        val result = testInstance.process(sequence)

        assertThat(result.totalDNAEditionRequestChecked).isEqualTo(3)
        assertThat(result.totalDeletions).isEqualTo(2)
        assertThat(result.totalInsertions).isEqualTo(0)
        assertThat(result.totalMutations).isEqualTo(0)
    }

    @Test
    fun `process two insertions one no_edition requests`() {
        val sequence = sequenceOf(
            DNAEditionCheckRequest(1, "AAACCC", "AAATCCC"),
            DNAEditionCheckRequest(2, "AAACCC", "AAATCGCC"),
            DNAEditionCheckRequest(3, "AAATTTGGG", "AAATTTGGG")
        )

        val result = testInstance.process(sequence)

        assertThat(result.totalDNAEditionRequestChecked).isEqualTo(3)
        assertThat(result.totalDeletions).isEqualTo(0)
        assertThat(result.totalInsertions).isEqualTo(2)
        assertThat(result.totalMutations).isEqualTo(0)
    }

    @Test
    fun `process two mutations one no_edition requests`() {
        val sequence = sequenceOf(
            DNAEditionCheckRequest(1, "AAATCCC", "AAAGCCC"),
            DNAEditionCheckRequest(2, "AAACCCGGG", "AAACCCAGG"),
            DNAEditionCheckRequest(3, "AAATTTGGG", "AAATTTGGG")
        )

        val result = testInstance.process(sequence)

        assertThat(result.totalDNAEditionRequestChecked).isEqualTo(3)
        assertThat(result.totalDeletions).isEqualTo(0)
        assertThat(result.totalInsertions).isEqualTo(0)
        assertThat(result.totalMutations).isEqualTo(2)
    }

    @Test
    fun `process one of each mutation and one no_edition requests`() {
        val sequence = sequenceOf(
            DNAEditionCheckRequest(1, "AAATCCC", "AAAGCCC"),
            DNAEditionCheckRequest(2, "AAATTTCCC", "AAATTTCCC"),
            DNAEditionCheckRequest(3, "AAATTTCCC", "AAATTCCC"),
            DNAEditionCheckRequest(4, "AAATGCCC", "AAATAGCCC"),
        )

        val result = testInstance.process(sequence)

        assertThat(result.totalDNAEditionRequestChecked).isEqualTo(4)
        assertThat(result.totalDeletions).isEqualTo(1)
        assertThat(result.totalInsertions).isEqualTo(1)
        assertThat(result.totalMutations).isEqualTo(1)
    }
}
