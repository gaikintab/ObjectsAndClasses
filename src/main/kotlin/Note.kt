package ru.netology

data class Note(
    val id: Long,
    val ownerId: Long,
    var title: String = "New note",
    var text: String = "Text",
    var date: Int = 0,
    var comments: Int = 0,
    var readComments: Int = 0,
    var viewUrl: String = "https://"
) {
    override fun toString(): String {
        return "$id"
    }
}

data class NoteComment(
    val noteId: Long,
    val id: Long,
    val ownerId: Long,
    val replyTo: Long = 0,
    var message: String = "Text",
    val guid: String = "",
    var isRemoved: Boolean = false
)

class Notes(var notes: MutableList<Note>) {
    var noteId: Long = 0
    fun add(
        ownerId: Long, title: String, text: String = "",
        privacyView: String = "all", privacyComment: String = "all"
    ): Long {

        notes.addLast(Note(noteId, ownerId, title, text))
        noteId++
        return notes.last().id
    }

    fun delete(noteId: Long): Long {
        for (note in notes) {
            if (note.id == noteId) {
                notes.remove(note)
                return 1
            }
        }
        throw NoteNotFoundException("Note Id# $noteId not found")
    }

    fun edit(
        noteId: Long, title: String = "", text: String = "",
        privacyView: String = "all", privacyComment: String = "all"
    ): Long {
        for (note in notes) {
            if (note.id == noteId) {
                if (title != "") note.title = title
                if (text != "") note.text = text
                return 1
            }
        }
        throw NoteNotFoundException("Note Id# $noteId not found")
    }

    fun get(): MutableList<Note> {
        return notes
    }

    fun getById(noteId: Long): Note {
        for (note in notes) {
            if (note.id == noteId) {
                return note
            }
        }
        throw NoteNotFoundException("Note Id# $noteId not found")
    }
}

class NoteComments(
    var noteComments: MutableList<NoteComment>,
    val notes: Notes
) {
    var commentId: Long = 0

    fun add(noteId: Long, ownerId: Long, replyTo: Long = 0, message: String = "", guid: String = ""): Long {
        for (note in notes.notes) {
            if (note.id == noteId) {
                noteComments.addLast(NoteComment(noteId, commentId, ownerId, message = message, guid = guid))
                commentId++
                return commentId - 1
            }
        }
        throw NoteNotFoundException("Note Id# $noteId for comment not found")
    }

    fun delete(commentId: Long, ownerId: Long): Long {
        for ((index, comment) in noteComments.withIndex()) {
            if (comment.id == commentId) {
                if (!comment.isRemoved) {
                    noteComments[index].isRemoved = true
                    return 1
                } else throw CommentNotFoundException("Comment Id# $commentId is already marked as DELETED")
            }
        }
        throw CommentNotFoundException("Comment Id# $commentId not found")
    }

    fun edit(commentId: Long, ownerId: Long, message: String = ""): Long {
        for (comment in noteComments) {
            if (comment.id == commentId) {
                if (!comment.isRemoved) {
                    comment.message = message
                    return 1
                } else throw CommentNotFoundException(
                    "Comment Id# $commentId marked as DELETED. " +
                            "Try to restore it"
                )
            }
        }
        throw CommentNotFoundException("Comment Id# $commentId not found")
    }

    fun restore(commentId: Long): Long {
        for (comment in noteComments) {
            if (comment.id == commentId) {
                if (comment.isRemoved) {
                    comment.isRemoved = false
                    return 1
                } else throw CommentNotFoundException("Comment Id# $commentId is not marked as DELETED")
            }
        }
        throw CommentNotFoundException("Comment Id# $commentId not found")
    }

    data class ListComments(
        val commentId: Long,
        val authorId: Long,
        val noteId: Long,
        val ownerId: Long,
        val text: String,
        val replyTo: Long
    )

    var returnList: MutableList<ListComments> = mutableListOf<ListComments>()
    fun getComments(
        noteId: Long, ownerId: Long, sort: Int = 0,
        offset: Int = 0, count: Int = 0
    ): MutableList<ListComments> {
        returnList.clear()
        var i = count
        for (comment in noteComments) {
            if (comment.noteId == noteId) {
                if (comment.ownerId == ownerId) {
                    if (!comment.isRemoved) {
                        returnList.addLast(
                            ListComments(
                                comment.id, comment.ownerId,
                                comment.noteId, comment.ownerId, comment.message, comment.replyTo
                            )
                        )
                        i--
                        if (i == 0) break      // счетчик
                    }
                }
            }
        }
        when (sort) {
            0 -> returnList.sortBy(ListComments::commentId)
            1 -> returnList.sortByDescending(ListComments::commentId)
        }
        return returnList
    }
}