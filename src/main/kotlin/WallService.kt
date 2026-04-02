package ru.netology

object WallService {

    private var posts = emptyArray<Post>()
    private var postId: Int = 0

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
                posts[index] = post.copy(id = uPost.id,
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
}