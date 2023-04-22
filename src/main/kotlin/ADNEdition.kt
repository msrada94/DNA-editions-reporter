enum class ADNEdition {
    NO_EDITION, DELETION, INSERTION, MUTATION;

    companion object {
        val EDITIONS: Set<ADNEdition> = setOf(
            DELETION, INSERTION, MUTATION
        )
    }
}
