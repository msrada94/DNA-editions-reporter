import ADNEdition.DELETION
import ADNEdition.INSERTION
import ADNEdition.MUTATION
import ADNEdition.NO_EDITION
import ADNEditionChecker.checkEdition
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ADNEditionTest {

    @Nested
    inner class NoEdition {
        @Test
        fun `no edition because did not change`() {
            val initialSeq = "AAATTTGGG"
            val finalSeq = "AAATTTGGG"

            val result = checkEdition(initialSeq, finalSeq)

            assertThat(result).isEqualTo(NO_EDITION)
        }

        @Test
        fun `no edition because change more than 1 base`() {
            val initialSeq = "AAATTCCC"
            val finalSeq = "AAAGGCCC"

            val result = checkEdition(initialSeq, finalSeq)

            assertThat(result).isEqualTo(NO_EDITION)
        }
    }

    @Nested
    inner class Deletion {
        @Test
        fun `deletion one base`() {
            val initialSeq = "AAACTTT"
            val finalSeq = "AAATTT"

            val result = checkEdition(initialSeq, finalSeq)

            assertThat(result).isEqualTo(DELETION)
        }

        @Test
        fun `deletion several bases`() {
            val initialSeq = "AAACCTTGT"
            val finalSeq = "AAATTT"

            val result = checkEdition(initialSeq, finalSeq)

            assertThat(result).isEqualTo(DELETION)
        }
    }

    @Nested
    inner class Insertion {
        @Test
        fun `insertion one base`() {
            val initialSeq = "AAACCC"
            val finalSeq = "AAATCCC"

            val result = checkEdition(initialSeq, finalSeq)

            assertThat(result).isEqualTo(INSERTION)
        }

        @Test
        fun `insertion several bases`() {
            val initialSeq = "AAATTT"
            val finalSeq = "AAACCTTGT"

            val result = checkEdition(initialSeq, finalSeq)

            assertThat(result).isEqualTo(INSERTION)
        }
    }

    @Nested
    inner class Mutation {
        @Test
        fun `mutation (change one base)`() {
            val initialSeq = "AAATCCC"
            val finalSeq = "AAAGCCC"

            val result = checkEdition(initialSeq, finalSeq)

            assertThat(result).isEqualTo(MUTATION)
        }

        @Test
        fun `not mutation because change more than one base`() {
            val initialSeq = "AATCCGCC"
            val finalSeq = "AAGCCTCC"

            val result = checkEdition(initialSeq, finalSeq)

            assertThat(result).isEqualTo(NO_EDITION)
        }
    }
}
