package ru.netology

import org.junit.Test
import kotlin.test.assertEquals

class NotesTest {
    var notes = Notes(mutableListOf())

    @Test
    fun add() {
        notes.add(1212, "First note", "Hello")
        notes.add(2312, "Second note", "Simply text")

        assertEquals(2, notes.add(2143, "Third note", "Such beautiful day"))
    }

    @Test
    fun delete() {
        notes.add(1212, "First note", "Hello")
        notes.add(2312, "Second note", "Simply text")

        assertEquals(1, notes.delete(1))
    }

    @Test
    fun edit() {
        notes.add(1212, "First note", "Hello")
        notes.add(2312, "Second note", "Simply text")

        assertEquals(1, notes.edit(1, "Second edited note", "Edited text"))
    }

    @Test
    fun get() {
        notes.add(1212, "First note", "Hello")
        notes.add(2312, "Second note", "Simply text")
        notes.add(2143, "Third note", "Such beautiful day")

        assertEquals("Third note", notes.get()[2].title)
    }

    @Test
    fun getById() {
        notes.add(1212, "First note", "Hello")
        notes.add(2312, "Second note", "Simply text")
        notes.add(2143, "Third note", "Such beautiful day")
        assertEquals("Second note", notes.getById(1).title)
    }

    @Test
    fun addComment() {
        notes.add(1212, "First note", "Hello")
        notes.add(2312, "Second note", "Simply text")
        notes.add(2143, "Third note", "Such beautiful day")
        val noteComm = NoteComments(mutableListOf(), notes)
        assertEquals(0,noteComm.add(1, 4537, message = "First comment 4537"))
    }

    @Test
    fun deleteComment() {
        notes.add(1212, "First note", "Hello")
        notes.add(2312, "Second note", "Simply text")
        notes.add(2143, "Third note", "Such beautiful day")
        val noteComm = NoteComments(mutableListOf(), notes)
        noteComm.add(1, 4537, message = "First comment 4537")
        noteComm.add(1, 4537, message = "Second comment 4537")

        assertEquals(1, noteComm.delete(1, 4537))
    }

    @Test
    fun editComment() {
        notes.add(1212, "First note", "Hello")
        notes.add(2312, "Second note", "Simply text")
        notes.add(2143, "Third note", "Such beautiful day")
        val noteComm = NoteComments(mutableListOf(), notes)
        noteComm.add(1, 4537, message = "First comment 4537")
        noteComm.add(1, 4537, message = "Second comment 4537")

        assertEquals(1, noteComm.edit(0, 4537, "Second edited comment"))
    }

    @Test
    fun restoreComment() {
        notes.add(1212, "First note", "Hello")
        notes.add(2312, "Second note", "Simply text")
        notes.add(2143, "Third note", "Such beautiful day")
        val noteComm = NoteComments(mutableListOf(), notes)
        noteComm.add(1, 4537, message = "First comment 4537")
        noteComm.add(1, 4537, message = "Second comment 4537")
        noteComm.delete(1, 4537)
        assertEquals(1, noteComm.restore(1))
    }

    @Test
    fun getComments() {
        notes.add(1212, "First note", "Hello")
        notes.add(2312, "Second note", "Simply text")
        notes.add(2143, "Third note", "Such beautiful day")
        val noteComm = NoteComments(mutableListOf(), notes)
        noteComm.add(1, 4537, message = "First comment 4537")
        noteComm.add(1, 4537, message = "Second comment 4537")

        assertEquals(2, noteComm.getComments(1, 4537).size)

    }

}