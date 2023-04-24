package utils

import model.DNAEdition.DELETION
import model.DNAEdition.INSERTION
import model.DNAEdition.MUTATION
import model.DNAEdition.NO_EDITION
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class EditionCheckerTest {
    private val testInstance = EditionChecker

    @Nested
    inner class NoEdition {
        @Test
        fun `no edition because did not change`() {
            val initialSeq = "AAATTTGGG"
            val finalSeq = "AAATTTGGG"

            val result = testInstance.check(initialSeq, finalSeq)

            assertThat(result).isEqualTo(NO_EDITION)
        }

        @Test
        fun `no edition because change more than 1 base`() {
            val initialSeq = "AAATTCCC"
            val finalSeq = "AAAGGCCC"

            val result = testInstance.check(initialSeq, finalSeq)

            assertThat(result).isEqualTo(NO_EDITION)
        }

        @Test
        fun `no edition because is not subsequence`() {
            val initialSeq = "ATCCTG"
            val finalSeq = "CTGC"

            val result = testInstance.check(initialSeq, finalSeq)

            assertThat(result).isEqualTo(NO_EDITION)
        }
    }

    @Nested
    inner class Deletion {
        @Test
        fun `deletion one base`() {
            val initialSeq = "AAACTTT"
            val finalSeq = "AAATTT"

            val result = testInstance.check(initialSeq, finalSeq)

            assertThat(result).isEqualTo(DELETION)
        }

        @Test
        fun `deletion several bases`() {
            val initialSeq = "AAACCTTGT"
            val finalSeq = "AAATTT"

            val result = testInstance.check(initialSeq, finalSeq)

            assertThat(result).isEqualTo(DELETION)
        }

        @Test
        fun `not deletion because is not subsequence`() {
            val initialSeq = "AAACCCTTT"
            val finalSeq = "AAACCCG"

            val result = testInstance.check(initialSeq, finalSeq)

            assertThat(result).isEqualTo(NO_EDITION)
        }
    }

    @Nested
    inner class Insertion {
        @Test
        fun `insertion one base`() {
            val initialSeq = "AAACCC"
            val finalSeq = "AAATCCC"

            val result = testInstance.check(initialSeq, finalSeq)

            assertThat(result).isEqualTo(INSERTION)
        }

        @Test
        fun `insertion several bases`() {
            val initialSeq = "AAATTT"
            val finalSeq = "AAACCTTGT"

            val result = testInstance.check(initialSeq, finalSeq)

            assertThat(result).isEqualTo(INSERTION)
        }

        @Test
        fun `not insertion because is not subsequence`() {
            val initialSeq = "AAATTT"
            val finalSeq = "AAAGGGCCC"

            val result = testInstance.check(initialSeq, finalSeq)

            assertThat(result).isEqualTo(NO_EDITION)
        }
    }

    @Nested
    inner class Mutation {
        @Test
        fun `mutation (change one base)`() {
            val initialSeq = "AAATCCC"
            val finalSeq = "AAAGCCC"

            val result = testInstance.check(initialSeq, finalSeq)

            assertThat(result).isEqualTo(MUTATION)
        }

        @Test
        fun `not mutation because change more than one base`() {
            val initialSeq = "AATCCGCC"
            val finalSeq = "AAGCCTCC"

            val result = testInstance.check(initialSeq, finalSeq)

            assertThat(result).isEqualTo(NO_EDITION)
        }
    }
}
