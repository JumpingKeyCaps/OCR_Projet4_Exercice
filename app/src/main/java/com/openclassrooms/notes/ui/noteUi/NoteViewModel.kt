package com.openclassrooms.notes.ui.noteUi

import androidx.lifecycle.ViewModel
import com.openclassrooms.notes.data.repository.NotesRepository
import com.openclassrooms.notes.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * The Note ViewModel
 *
 * Use Hilt to inject the used NoteRepository by the constructor
 *
 */
@HiltViewModel
class NoteViewModel @Inject constructor(private val notesRepository: NotesRepository) : ViewModel() {


    /**
     * Fetch the note list,
     * by Exposing directly the Flow of the noteRepository
     * @return A Flow with the notes list.
     */
    val notes: Flow<List<Note>> =  notesRepository.notes

    /**
     * Add a note to the list
     * @param newNote The new note object to add to the list.
     */
    fun addNote(newNote:Note) = notesRepository.addNote(newNote)

    /**
     * Remove a note to the list of notes.
     *
     * Use one of the 3 parameters to remove a note object.
     *
     * @param index (optional) the index position of the note to remove.
     * @param noteObject (optional) the note object reference to remove of the list.
     * @param title (optional) the title of the note to remove.
     */
    fun removeNote(index: Int?, noteObject:Note?, title: String?) = notesRepository.removeNote(index,noteObject,title)



    /**
     * Si on a besoin d'un contrôle spécifique sur la valeur actuelle de notre liste de notes dans le ViewModel
     * on peut utiliser StateFlow , plus proche du fonctionement d'un livedata
     *  ---> l'inconvenient est l'utilisation de 2 coroutines pour collecter nos 2 flow (une dans le fragment et une dans le viewmodel)
     *
     * ------------------------------------------------------------------------
     * private val _notes = MutableStateFlow<List<Note>>(emptyList())
     * val notes: StateFlow<List<Note>> = _notes.asStateFlow()
     *
     * init {
     *   viewModelScope.launch {
     *       notesRepository.notes.collect {
     *           _notes.value = it
     *       }
     *   }
     * }
     *
     * --------------------------------------------------------------------------
     **/

}