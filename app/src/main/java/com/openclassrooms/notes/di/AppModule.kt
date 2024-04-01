package com.openclassrooms.notes.di

import com.openclassrooms.notes.data.repository.NotesRepository
import com.openclassrooms.notes.data.service.LocalNotesApiService
import com.openclassrooms.notes.ui.noteUi.NoteViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * The Hilt Module who provide instance to Dependency injections.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides a singleton instance of the NotesRepository.
     *
     * @param localNotesApiService the used api by the note repository
     * @return A singleton instance of the NotesRepository.
     */
    @Provides
    fun provideNotesRepository(localNotesApiService: LocalNotesApiService): NotesRepository {
        return NotesRepository(localNotesApiService)
    }

    /**
     * Provides a singleton instance of the LocalNotesApiService.
     *
     * @return A singleton instance of the LocalNotesApiService.
     */
    @Provides
    fun provideLocalNotesApiService(): LocalNotesApiService {
        return LocalNotesApiService()
    }

    /**
     * Provides a singleton instance of the NoteViewModel.
     *
     * @param notesRepository the used repository by the ViewModel.
     * @return A singleton instance of the NoteViewModel.
     */
    @Provides
    fun provideNoteViewModel(notesRepository: NotesRepository): NoteViewModel{
        return NoteViewModel(notesRepository)
    }



}