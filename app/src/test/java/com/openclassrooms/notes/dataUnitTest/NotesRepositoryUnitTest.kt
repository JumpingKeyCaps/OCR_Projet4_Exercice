package com.openclassrooms.notes.dataUnitTest

import com.openclassrooms.notes.data.repository.NotesRepository
import com.openclassrooms.notes.data.service.NotesApiService
import com.openclassrooms.notes.model.Note
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/**
 * Test class for the Notes Repository
 */
class NotesRepositoryUnitTest {


    /**
     * Mocked class
     */
    @Mock
    private lateinit var mockNotesApiService: NotesApiService
    private lateinit var notesRepository: NotesRepository

    /**
     * Test setup
     */
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this) // Initialize mocks before each test
        notesRepository = NotesRepository(mockNotesApiService)

    }


    /**
     * Test the flow of notes list emits by the repository
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `notes flow emits all notes from the api service`() {
        val expectedNotes = listOf(Note("Titre1", "Contenu1"), Note("Titre2", "Contenu2"))
        `when`(mockNotesApiService.getAllNotes()).thenReturn(expectedNotes.toMutableList())

        val capturedNotes = mutableListOf<List<Note>>()

        val testCoroutineScope = TestCoroutineScope() // Use TestCoroutineScope for coroutines
        testCoroutineScope.launch {
            notesRepository.notes.collect { capturedNotes.add(it) } // Collect emitted values
        }

        assertEquals(expectedNotes, capturedNotes.first())

    }

    /**
     * Test the addNote method behavior.
     */
    @Test
    fun `addNote calls notesApiService to add the note`() {
        val newNote = Note("Titre3", "Contenu3")

        doNothing().`when`(mockNotesApiService).addNote(newNote) // Use doNothing() for void method

        notesRepository.addNote(newNote)

        verify(mockNotesApiService).addNote(newNote) // Verify the call
    }

    /**
     * Test the removeNote method behavior.
     */
    @Test
    fun `removeNote calls notesApiService with the provided index`() {
        val indexToRemove = 2
        doNothing().`when`(mockNotesApiService).removeNote(indexToRemove, null, null) // Use doNothing() for void method

        notesRepository.removeNote(indexToRemove, null, null)

        verify(mockNotesApiService).removeNote(indexToRemove, null, null) // Verify call with index
    }


}