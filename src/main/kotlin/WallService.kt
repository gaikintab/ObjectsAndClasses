package ru.netology

object WallService {

    private var posts = emptyArray<Post>()
    private var postId: Int = 1
    private var comments = emptyArray<Comment>()
    private var commentReports: Array<CommentReport> = emptyArray()

    data class CommentReport(
        val ownerId: Int,
        val commentId: Int,
        val reason: Int
    )

    fun addPost(post: Post): Post {
        if (!posts.isEmpty()) {
            postId = posts.last().id + 1
        }
        post.id = postId
        posts += post
        return posts.last()
    }

    fun updatePost(uPost: Post): Boolean {
        var findPost = false
        for ((index, post) in posts.withIndex()) {
            if (uPost.id == post.id) {
                posts[index] = post.copy(
                    id = uPost.id,
                    ownerId = uPost.ownerId,
                    fromId = uPost.fromId,
                    replyOwnerId = uPost.replyOwnerId,
                    replyPostId = uPost.replyPostId,
                    date = uPost.date,
                    text = uPost.text,
                    isFavorite = uPost.isFavorite,
                    markedAsAds = uPost.markedAsAds,
                    comment = uPost.comment,
                    likes = uPost.likes
                )
                findPost = true
                break
            }
        }
        return findPost
    }

    fun clearService() {
        posts = emptyArray()
        postId = 1
        comments = emptyArray()
    }

    fun getPosts(): Array<Post> {
        return posts
    }

    fun addComment(pId: Int, comment: Comment): Comment {
        var findPost = false
        for((index, post) in posts.withIndex()) {
            if (post.id == pId) {
                comments += comment
                posts[index] = post.copy(comment = comments)
                findPost = true
            }
        }
        return if (findPost) {comments.last()} else throw PostNotFoundException("Post with id $pId not found")
    }

    fun reportComment(ownerId: Int, commentId: Int, reason: Int): Boolean {
        var findComment = false
        for (comment in comments) {
            if (comment.id == commentId) {
                if (reason in 0..8) {
                    commentReports += CommentReport(ownerId, commentId, reason)
                    findComment = true
                } else {
                    throw ReasonNotFoundException("Reason $reason in incorrect. Must be from 0 to 8")
                }
            }
        }
        return if (findComment) {findComment} else
            throw CommentNotFoundException("Comment with id $commentId not found")
    }
}