package com.faisalgehlot.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: NoteRepository(NoteDataBase)
    val allNotes: LiveData<List<Note>>

    init {
        val dao = NoteDataBase.getDatabase(application).getNoteDao()
        val repository = NoteRepository(dao)
        allNotes = repository.allNote
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(note)
    }
    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
    }
}