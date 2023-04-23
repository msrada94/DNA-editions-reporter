package utils

import model.DNAEdition
import model.DNAEdition.DELETION
import model.DNAEdition.INSERTION
import model.DNAEdition.MUTATION
import model.DNAEdition.NO_EDITION
import model.DNAEditionsReport

object EditionsCounter {
    fun count(editions: Sequence<DNAEdition>): DNAEditionsReport {
        val report = DNAEditionsReport()
        editions.forEach { edition -> updateReport(edition, report) }

        return report
    }

    private fun updateReport(edition: DNAEdition, report: DNAEditionsReport) {
        report.totalChecks += 1
        when (edition) {
            DELETION -> report.totalDeletions += 1
            INSERTION -> report.totalInsertions += 1
            MUTATION -> report.totalMutations += 1
            NO_EDITION -> {}
        }
    }
}
