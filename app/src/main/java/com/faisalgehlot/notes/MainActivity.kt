package com.faisalgehlot.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.faisalgehlot.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ClickedNotesRVAdapter {

    private lateinit var viewModel: NoteViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitButton.setOnClickListener {
            submitButton()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRecyclerViewAdapter(this)
        binding.recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]
        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(it)
            }
        })
    }
    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.text} Delete", Toast.LENGTH_SHORT).show()
    }

    private fun submitButton(){
        val noteText = binding.input.text.toString()
        if (noteText.isNotEmpty()) {
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this,"$noteText Inserted", Toast.LENGTH_SHORT).show()
        }
    }
}