package com.openclassrooms.notes.ui.noteUi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.openclassrooms.notes.R
import com.openclassrooms.notes.data.repository.NotesRepository
import com.openclassrooms.notes.databinding.FragmentNoteBinding
import com.openclassrooms.notes.ui.noteUi.decoration.NoteItemDecoration
import com.openclassrooms.notes.ui.noteUi.adapter.NotesAdapter
import kotlinx.coroutines.launch

/**
 * The Fragment for the list of Notes Screen of the App.
 */
class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding
    private val notesAdapter = NotesAdapter(emptyList())
    private val notesRepository = NotesRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initFABButton()
        collectNotes()


    }

    /**
     * Initializes the RecyclerView.
     */
    private fun initRecyclerView() {
        with(binding.recycler) {
            addItemDecoration(
                NoteItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.default_margin),
                    resources.getInteger(R.integer.span_count)
                )
            )

            adapter = notesAdapter
        }

    }


    /**
     * Initializes the FAB button.
     */
    private fun initFABButton() {
        binding.btnAdd.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext()).apply {
                setTitle(R.string.coming_soon)
                setMessage(R.string.not_available_yet)
                setPositiveButton(android.R.string.ok, null)
            }.show()
        }
    }


    /**
     * Collects notes from the repository and updates the adapter.
     */
    private fun collectNotes() {
        lifecycleScope.launch {
            notesRepository.notes.collect {
                notesAdapter.updateNotes(it)
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment NoteFragment.
         */
        @JvmStatic
        fun newInstance() = NoteFragment()
    }
}