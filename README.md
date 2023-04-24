# DNA Editions Reporter

This service reports how many DNA editions are found of each type from a provided TSV file containing the requests to be checked. This project is written in Kotlin and uses Gradle as its build automation tool.

In particular, this service is used in the project checking a TSV file named seq.tsv located in the base of this project, given as a result
after have checked 8 DNA Edition requests:

| Metric                          | Value |
|---------------------------------|-------|
| Total DNA Edition Requests      | 8     |
| Total Deletions                 | 2     |
| Total Insertions                | 1     |
| Total Mutations                 | 2     |

## About the package organisation

This project is packaged by type having packages: 
* exceptions
* utils
* services
* model

## About the dependencies

The dependencies used are:
* JUnit5
* AssertJ
