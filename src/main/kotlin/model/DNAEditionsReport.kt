package model

data class DNAEditionsReport(
    var totalChecks: Int = 0,
    var totalDeletions: Int = 0,
    var totalInsertions: Int = 0,
    var totalMutations: Int = 0
)
