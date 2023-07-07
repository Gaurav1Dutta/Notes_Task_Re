package com.example.notes_task_re.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes_task_re.R
import com.example.notes_task_re.entites.NotesEntity
import com.example.notes_task_re.`interface`.NotesDbInterface

@Database(entities = [NotesEntity::class], version = 1, exportSchema = false )
abstract class NotesDb : RoomDatabase() {
     abstract fun notesDbInterface() : NotesDbInterface

    companion object{
        var notesDb : NotesDb? =null
        @Synchronized
        fun getDataBase(context: Context): NotesDb{
            if (notesDb == null)
            {
                notesDb = Room.databaseBuilder(
                    context,NotesDb::class.java,   context.resources.getString(R.string.app_name)).build()
            }
            return notesDb!!
        }

    }

}