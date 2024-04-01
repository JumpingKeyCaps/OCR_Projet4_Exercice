package com.openclassrooms.notes.data.service

import com.openclassrooms.notes.model.Note

/**
 * Defines the contract for the API that manages the notes
 */
interface NotesApiService {

    /**
     * Add a note
     * @param note The note to add
     */
    fun addNote(note: Note)

    /**
     * Returns all the notes
     * @return the list of notes
     */
    fun getAllNotes(): MutableList<Note>

    /**
     * Remove a notes
     * @param title null by default (can be omitted), used to remove by the title of the note.
     * @param index null by default (can be omitted), used to remove by the index position of the note.
     * @param noteObject null by default (can be omitted), used to remove by the object ref directly.
     */
    fun removeNote(index: Int? = null, noteObject:Note? = null, title: String? = null)

}