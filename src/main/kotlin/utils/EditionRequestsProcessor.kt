package utils

import model.DNAEditionCheckRequest
import model.DNAEditionsReport

object EditionRequestsProcessor {
    fun process(requests: Sequence<DNAEditionCheckRequest>): DNAEditionsReport {
        val editions = requests.map { request -> EditionChecker.check(request.initialSeq, request.finalSeq) }
        return EditionsCounter.count(editions)
    }
}
