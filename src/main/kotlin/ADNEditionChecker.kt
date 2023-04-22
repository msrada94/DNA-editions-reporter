import ADNEdition.DELETION
import ADNEdition.INSERTION
import ADNEdition.MUTATION
import ADNEdition.NO_EDITION

object ADNEditionChecker {
    fun checkEdition(initialSeq: String, finalSeq: String): ADNEdition {
        val lengthInitial = initialSeq.length
        val lengthFinal = finalSeq.length

        val result = if (lengthInitial == lengthFinal) {
            if (isMutation(initialSeq, finalSeq)) MUTATION else NO_EDITION
        } else if (lengthInitial > lengthFinal) {
            if (isDeletion(initialSeq, finalSeq)) DELETION else NO_EDITION
        } else {
            if (isInsertion(initialSeq, finalSeq)) INSERTION else NO_EDITION
        }

        return result
    }

    private fun isMutation(initial: String, final: String): Boolean {
        var differences = 0
        for (i in initial.indices) {
            if (initial[i] != final[i]) {
                differences += 1
                if (differences > 1) return false
            }
        }
        return differences == 1
    }

    private fun isDeletion(initial: String, final: String): Boolean =
        isSubSequence(string = initial, subString = final)

    private fun isInsertion(initial: String, final: String): Boolean =
        isSubSequence(string = final, subString = initial)

    private fun isSubSequence(string: String, subString: String): Boolean {
        val matchingChars = string.filter { it in subString }

        return matchingChars.fold(0) { index, char ->
            if (char == subString[index]) index + 1 else index
        } == subString.length
    }
}
