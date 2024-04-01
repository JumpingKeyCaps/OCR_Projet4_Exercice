package com.openclassrooms.notes.data.repository

import com.openclassrooms.notes.data.service.LocalNotesApiService
import com.openclassrooms.notes.data.service.NotesApiService
import com.openclassrooms.notes.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository class for the notes.
 */
class NotesRepository {

    /**
     * The API service for interacting with notes.
     */
    private val notesApiService: NotesApiService = LocalNotesApiService()

    /**
     * A flow that emits a list of all notes.
     */
    val notes: Flow<List<Note>> = flow { emit(notesApiService.getAllNotes()) }

    /**
     * Add a note to the list of notes
     * @param newNote The new note object to add to the list.
     */
    fun addNote(newNote:Note) = notesApiService.addNote(newNote)

    /**
     * Remove a note to the list of notes.
     *
     * Use one of the 3 parameters to remove a note object.
     *
     * @param index (optional) the index position of the note to remove.
     * @param noteObject (optional) the note object reference to remove of the list.
     * @param title (optional) the title of the note to remove.
     */
    fun removeNote(index: Int?, noteObject:Note?, title: String?) = notesApiService.removeNote(index,noteObject,title)

}