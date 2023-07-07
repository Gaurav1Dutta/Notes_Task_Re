package com.example.notes_task_re.`interface`

import com.example.notes_task_re.entites.NotesEntity
interface ClickInterface {
    fun  onDeleteClick(notes_entities: NotesEntity)

    fun onEditClick(notes_entities: NotesEntity)
}