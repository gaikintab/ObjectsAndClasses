package ru.netology

data class Post(
    var id: Int,
    val ownerId: Int,
    val fromId: Int,
    val replyOwnerId: Int,
    val replyPostId: Int,
    val date: Int,
    val text: String = "",
    val isFavorite: Boolean = false,
    val markedAsAds: Boolean = false,
    val comment: Array<Comment>?,
    val likes: Likes?,
    val attachments: Array<Attachment>?
)

data class Comment(
    val id: Int,
    val fromId: Int,
    val date: Int,
    val text: String = "",
    val donut: Boolean = false,
    val replyToUser: Int,
    val replyToComment: Int,
    val attachments: Array<Attachment>?,
    val parentsStack: Array<Int>?,
    val thread: ThreadComments?
)

data class ThreadComments(
    val count: Int,
    val canPost: Boolean,
    val showReplyButton: Boolean,
    val groupsCanPost: Boolean
)

data class Likes(
    val count: Int,
    val userLikes: Boolean,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
)