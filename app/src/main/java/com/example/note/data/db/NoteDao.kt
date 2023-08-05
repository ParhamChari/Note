package com.example.note.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.note.data.model.NoteModel

@Dao
interface NoteDao {

    @Insert
    suspend fun addNote(note : NoteModel)

    @Update
    suspend fun updateNote(note : NoteModel)

    @Delete
    suspend fun deleteNote(note : NoteModel)

    @Query("SELECT * FROM note_table ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<NoteModel>>

    @Query("SELECT * FROM note_table WHERE title LIKE:query OR description LIKE:query")
    fun searchNotes(query : String?) : LiveData<List<NoteModel>>
}