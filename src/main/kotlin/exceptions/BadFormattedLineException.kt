package exceptions

class BadFormattedLineException(line: String) : IllegalArgumentException(("The line \"${line}\" is not well-formatted"))
