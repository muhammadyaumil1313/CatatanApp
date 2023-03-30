package org.d3if00001.catatanapp.notes.database

import androidx.lifecycle.LiveData
import androidx.room.*
import org.d3if00001.catatanapp.notes.entity.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)
    @Update
    fun update(note: Note)
    @Delete
    fun delete(note: Note)
    @Query("SELECT * from note ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Note>>
}