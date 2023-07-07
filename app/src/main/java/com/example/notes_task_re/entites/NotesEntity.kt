package com.example.notes_task_re.entites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NotesEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    @ColumnInfo()
    var title : String? = null,
    @ColumnInfo()
    var description : String?= null,
    @ColumnInfo()
    var image: String?= null,
    @ColumnInfo()
    var date: String?= null,
    @ColumnInfo()
    var time: String?= null,
)
