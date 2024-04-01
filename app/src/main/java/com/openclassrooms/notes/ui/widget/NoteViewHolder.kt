package com.openclassrooms.notes.ui.widget

import androidx.recyclerview.widget.RecyclerView
import com.openclassrooms.notes.databinding.NoteBinding

/**
 * A view holder for displaying a note in a RecyclerView.
 * @param binding The binding for the note layout.
 */
class NoteViewHolder(private val binding: NoteBinding): RecyclerView.ViewHolder(binding.root) {

    /**
     * Binds the view holder to a note.
     * @param note The note to bind to the view holder.
     */
    fun bind(note: Pair<String, String>) {
        binding.title.text = note.first
        binding.body.text = note.second
    }

}