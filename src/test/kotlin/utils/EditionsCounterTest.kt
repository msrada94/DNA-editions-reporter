package utils

import model.DNAEdition.DELETION
import model.DNAEdition.INSERTION
import model.DNAEdition.MUTATION
import model.DNAEdition.NO_EDITION
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EditionsCounterTest {
    private val testInstance = EditionsCounter

    @Test
    fun `count three no editions`() {
        val editions = sequenceOf(NO_EDITION, NO_EDITION, NO_EDITION)

        val result = testInstance.count(editions)

        assertThat(result.totalChecks).isEqualTo(3)
        assertThat(result.totalDeletions).isEqualTo(0)
        assertThat(result.totalInsertions).isEqualTo(0)
        assertThat(result.totalMutations).isEqualTo(0)
    }

    @Test
    fun `count two deletions one no edition`() {
        val editions = sequenceOf(DELETION, DELETION, NO_EDITION)

        val result = testInstance.count(editions)

        assertThat(result.totalChecks).isEqualTo(3)
        assertThat(result.totalDeletions).isEqualTo(2)
        assertThat(result.totalInsertions).isEqualTo(0)
        assertThat(result.totalMutations).isEqualTo(0)
    }

    @Test
    fun `count two insertions one no edition`() {
        val editions = sequenceOf(INSERTION, INSERTION, NO_EDITION)

        val result = testInstance.count(editions)

        assertThat(result.totalChecks).isEqualTo(3)
        assertThat(result.totalDeletions).isEqualTo(0)
        assertThat(result.totalInsertions).isEqualTo(2)
        assertThat(result.totalMutations).isEqualTo(0)
    }

    @Test
    fun `count two mutations one no edition`() {
        val editions = sequenceOf(MUTATION, MUTATION, NO_EDITION)

        val result = testInstance.count(editions)

        assertThat(result.totalChecks).isEqualTo(3)
        assertThat(result.totalDeletions).isEqualTo(0)
        assertThat(result.totalInsertions).isEqualTo(0)
        assertThat(result.totalMutations).isEqualTo(2)
    }

    @Test
    fun `count random sequence`() {
        val editions = sequenceOf(NO_EDITION, DELETION, INSERTION, INSERTION, MUTATION, MUTATION, MUTATION)

        val result = testInstance.count(editions)

        assertThat(result.totalChecks).isEqualTo(7)
        assertThat(result.totalDeletions).isEqualTo(1)
        assertThat(result.totalInsertions).isEqualTo(2)
        assertThat(result.totalMutations).isEqualTo(3)
    }
}
