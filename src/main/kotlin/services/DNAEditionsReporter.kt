package services

import model.DNAEditionsReport
import utils.EditionRequestsProcessor
import utils.TSVFileReader
import java.io.File

object DNAEditionsReporter {
    fun readTSVFile(file: File): DNAEditionsReport {
        val requests = TSVFileReader.getCheckingRequests(file)

        return EditionRequestsProcessor.process(requests)
    }
}
