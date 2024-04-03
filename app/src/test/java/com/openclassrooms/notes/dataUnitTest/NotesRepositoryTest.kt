package com.openclassrooms.notes.dataUnitTest

import com.openclassrooms.notes.data.repository.NotesRepository
import com.openclassrooms.notes.data.service.NotesApiService
import com.openclassrooms.notes.model.Note
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Test class for the Notes Repository
 */
class NotesRepositoryTest {


    /**
     * Mocked class
     */
    @MockK
    private lateinit var mockNotesApiService: NotesApiService
    private lateinit var notesRepository: NotesRepository

    /**
     * Test setup
     */
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        notesRepository = NotesRepository(mockNotesApiService)

    }

    // New test with MockK et runTest()

    /**
     * Test the flow of notes list emits by the repository
     */
    @Test
    fun `notes flow emits all notes from the api service`() {
        //mocked data
        val expectedNotes = listOf(Note("Titre1", "msg1"), Note("Titre2", "msg2"))
        //mocked Api behavior
        //use of coEvery function from mockK to define expectations for suspending functions like getAllNotes()
        coEvery { mockNotesApiService.getAllNotes() } returns expectedNotes.toMutableList()

        //Running the test with coroutine scope
        //notesRepository is a "cold flow" (emits values over time), this is why i use a coroutine to test it.
        runTest {
            val capturedNotes = mutableListOf<List<Note>>()
            //Capturing emitted value
            notesRepository.notes.collect { capturedNotes.add(it) }
            //Assert: test expected value with the collected value across the flow.
            assertEquals(expectedNotes, capturedNotes.first())
        }
    }

    /**
     * Test the addNote method behavior.
     */
    @Test
    fun `addNote calls notesApiService to add the note`() {
        //Creating Test Data
        val newNote = Note("Titre3", "msg3")

        //Mocking API behavior
        //use of every function from MockK to define the behavior of the mocked mockNotesApiService for the addNote() function.
        every { mockNotesApiService.addNote(newNote) } returns Unit

        //act:
        notesRepository.addNote(newNote)

        // verifies that the mocked addNote function was called exactly once
        // with the same newNote object that was passed during the test execution.
        // This ensures the repository interacts with the API service as expected when adding a new note
        verify(exactly = 1) { mockNotesApiService.addNote(newNote) }

    }

    /**
     * Test the removeNote method behavior.
     */
    @Test
    fun `removeNote removes the note at the given index from the repository`() {
        //Creating Test Data
        val indexToRemove = 2
        val expectedNotes = mutableListOf(Note("Titre1", "msg1"), Note("Titre2", "msg2"))
        //Mocking API behavior
        coEvery { mockNotesApiService.getAllNotes() } returns expectedNotes.toMutableList()
        coEvery { mockNotesApiService.removeNote(indexToRemove, null, null) } returns Unit
        //Running the test with coroutine scope
        runTest {
            notesRepository.removeNote(indexToRemove, null, null)
            notesRepository.notes.collect {
                // Assert to check if the note was removed
                val actualSize = expectedNotes.size
                val subList1 = expectedNotes.subList(0, indexToRemove.coerceAtMost(actualSize))
                val subList2 = expectedNotes.subList((indexToRemove + 1).coerceAtMost(actualSize), actualSize)
                assertEquals(subList1 + subList2, it)
            }
        }
    }



}