package com.openclassrooms.notes.dataUnitTest

import com.openclassrooms.notes.data.service.LocalNotesApiService
import com.openclassrooms.notes.model.Note
import junit.framework.TestCase.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Test Class for the LocalNoteApiService
 */
class LocalNotesApiServiceTest {



    private lateinit var notesApiService: LocalNotesApiService

    /**
     * Tests Setup
     */
    @Before
    fun setUp() {
       notesApiService = LocalNotesApiService()
    }

    /**
     * Test the correct behavior of the addNote method.
     */
    @Test
    fun `addNote should add a note to the list`() {
        // Given
        val note = Note("Titre", "Contenu")

        // When
        notesApiService.addNote(note)

        // Then
        val allNotes = notesApiService.getAllNotes()
        val foundNote = allNotes.find { it.titre == note.titre && it.message == note.message }
        assertNotNull(foundNote)
    }

    /**
     * Test the correct behavior of the remove Note method, for a remove by the note index.
     */
    @Test
    fun `removeNote by index should remove the note at the given index`() {
        // Given
        val note1 = Note("Titre1", "Contenu1")
        val note2 = Note("Titre2", "Contenu2")
        val note3 = Note("Titre3", "Contenu3")
        notesApiService.addNote(note1)
        notesApiService.addNote(note2)
        notesApiService.addNote(note3)

        val allNotes = notesApiService.getAllNotes()

        val expectedSize = allNotes.size - 1 // Expected size after removal

        // When
        notesApiService.removeNote(index = allNotes.size - 2) //remove note2 (from the end of the list)

        // Then
        assertEquals(expectedSize, allNotes.size) // Verify list size reduction
        assertTrue(allNotes.contains(note1)) // Verify note1 is present
        assertTrue(allNotes.contains(note3)) // Verify note3 is present
        assertFalse(allNotes.contains(note2)) // Verify note2 is absent
    }


    /**
     * Test the correct behavior of the remove Note method, for a remove by Note Object.
     */
    @Test
    fun `removeNote by object should remove the note with the given object reference`() {
        // Given
        val note1 = Note("Titre1", "Contenu1")
        val note2 = Note("Titre2", "Contenu2")
        notesApiService.addNote(note1)
        notesApiService.addNote(note2)

        // When
        notesApiService.removeNote(noteObject = note1)

        // Then
        val allNotes = notesApiService.getAllNotes()
        assertFalse(allNotes.contains(note1)) // Verify note1 is absent
        assertTrue(allNotes.contains(note2)) // Verify note2 is present
    }

    /**
     * Test the correct behavior of the remove Note method, for a remove by note Title.
     */
    @Test
    fun `removeNote by title should remove the note with the given title`() {
        // Given
        val note1 = Note("Titre1", "Contenu1")
        val note2 = Note("Titre2", "Contenu2")
        notesApiService.addNote(note1)
        notesApiService.addNote(note2)

        // When
        notesApiService.removeNote(title = "Titre1")

        // Then
        val allNotes = notesApiService.getAllNotes()
        assertFalse(allNotes.any { it.titre == "Titre1" }) // Verify note1 is absent by title
        assertTrue(allNotes.contains(note2)) // Verify note2 is present
    }

    /**
     * Test the correct behavior of the get all notes method.
     */
    @Test
    fun `getAllNotes should return all the notes in the list`() {
        // Given
        val targetTestListSize = (notesApiService.getAllNotes().size + 2)
        val note1 = Note("Titre1", "Contenu1")
        val note2 = Note("Titre2", "Contenu2")
        notesApiService.addNote(note1)
        notesApiService.addNote(note2)

        // When
        val allNotes = notesApiService.getAllNotes()


        // Then
        assertTrue(allNotes.contains(note1)) // Verify note1 is present
        assertTrue(allNotes.contains(note2)) // Verify note2 is present
        assertEquals(targetTestListSize, allNotes.size) // Verify list size matches
    }

    /**
     * Test the correct behavior of the generate default notes list.
     */
    @Test
    fun `test GenerateDefaultNotes behavior`() {
        val method = notesApiService::class.java.getDeclaredMethod("generateDefaultNotes")
        method.isAccessible = true // Rend la méthode accessible
        val defaultNotes = method.invoke(notesApiService) as List<*>

        // Assertions sur defaultNotes
        assertTrue(defaultNotes.isNotEmpty())
    }


}