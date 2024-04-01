package com.openclassrooms.notes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.openclassrooms.notes.R
import com.openclassrooms.notes.databinding.ActivityMainBinding
import com.openclassrooms.notes.ui.noteUi.NoteFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * The main activity for the app.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /**
     * The binding for the main layout.
     */
    private lateinit var binding: ActivityMainBinding

    /**
     * Life Cycle -
     * Called when the activity is created.
     * @param savedInstanceState the saved state bundle.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity_fragment_Container, NoteFragment.newInstance())
                .commitNow()
        }
    }


}
