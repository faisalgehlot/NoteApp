package com.faisalgehlot.notes

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.*

@Dao
interface NoteDao {

    @Insert(onConflict = IGNORE)
    fun insert (note: Note)

    @Delete
    fun delete (note: Note)

    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes (): LiveData< List<Note> >
}