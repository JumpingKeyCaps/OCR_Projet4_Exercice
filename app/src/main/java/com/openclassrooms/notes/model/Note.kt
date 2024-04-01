package com.openclassrooms.notes.model

/**
 * A data class for a model to the Note data.
 * @param titre the Title of the Note Object.
 * @param message the Corp message of the Note object.
 */
data class Note(
    val titre: String,
    val message: String,
)
