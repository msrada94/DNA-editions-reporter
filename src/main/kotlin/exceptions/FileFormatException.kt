package exceptions

class FileFormatException(fileName: String, format: String) :
    RuntimeException("the file $fileName does not have $format format")
