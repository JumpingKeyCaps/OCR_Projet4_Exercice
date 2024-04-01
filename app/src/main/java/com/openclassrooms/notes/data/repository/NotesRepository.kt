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
    val notes: Flow<List<Note>> = flow {
        emit(notesApiService.getAllNotes())
    }
}