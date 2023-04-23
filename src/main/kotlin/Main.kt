import services.DNAEditionsReporter
import java.io.File

fun main() {
    val file = File("seq.tsv")
    val report = DNAEditionsReporter.readTSVFile(file)

    print(report)
}
