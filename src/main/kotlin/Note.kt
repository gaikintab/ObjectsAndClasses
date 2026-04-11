package ru.netology

data class Note(
    val id: Long,
    val ownerId: Long,
    val title: String = "New note",
    val text: String = "Text",
    val date: Int,
    val comments: Int = 0,
    val readComments: Int = 0,
    val viewUrl: String = "https://"
)

data class NoteComment(
    val noteId: Long,
    val ownerId: Long,
    val replyTo: Long = 0,
    val message: String = "Text",
    val guid: String,
)

interface OperationService<T>  {
    fun add(obj: T): Long
    fun remove(id: Long): Boolean
    fun edit(obj: T): Boolean
    fun restore(id: Long): Boolean
    fun get(id: Int): List<T>
    fun getById(id: Long): T
}

class NoteService<Note>(val notes: List<Note>) : OperationService<Note> {


    override fun add(obj: Note): Long {


        return 0
    }

    override fun remove(id: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun edit(obj: Note): Boolean {
        TODO("Not yet implemented")
    }

    override fun restore(id: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun get(id: Int): List<Note> {
        TODO("Not yet implemented")
    }

    override fun getById(id: Long): Note {
        TODO("Not yet implemented")
    }

}