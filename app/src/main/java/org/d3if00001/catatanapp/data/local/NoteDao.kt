package org.d3if00001.catatanapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import org.d3if00001.catatanapp.domain.models.Note

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