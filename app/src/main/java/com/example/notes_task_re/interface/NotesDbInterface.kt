package com.example.notes_task_re.`interface`

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notes_task_re.entites.NotesEntity

@Dao
interface NotesDbInterface {
    @Insert
   fun InsertNotes(notes: NotesEntity) : Long//variable : entity , now connect dao with database

    @Query("Select* From NotesEntity")
    fun getNotes():List<NotesEntity>

    @Delete
    fun DeleteNotes(notes_entities : NotesEntity) //to delete

    @Update
    fun UpdateNotes(notes_entities: NotesEntity)//to edit / update

    @Query("SELECT * FROM  NotesEntity WHERE id = :id")
    fun getNotesById(id: Int) : NotesEntity

}