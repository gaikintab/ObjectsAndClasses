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
    val comment: Comment?,
    val likes: Likes?,
    val attachments: Array<Attachment>
)

data class Comment(
    val count: Int,
    val canPost: Boolean = false,
    val groupsCanPost: Boolean = false,
    val canClose: Boolean = false,
    val canOpen: Boolean = false
)

data class Likes(
    val count: Int,
    val userLikes: Boolean,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
)