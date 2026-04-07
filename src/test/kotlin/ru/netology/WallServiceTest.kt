package ru.netology

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import ru.netology.WallService.addPost
import ru.netology.WallService.updatePost

class WallServiceTest {

    val likes = Likes(5, true)
    val comment = Comment(1)
    val audio: Audio = Audio(1, 2, "Any", "Chorus", 75, "",
    25, 100, 122, 10000, noSearch = true, isHQ = true)
    val attachment: Attachment = Attachment.AudioAttachment(audio)
    var attachments = emptyArray<Attachment>()

//    val post = Post(
//        2, 12345, 54321, 2323, 4545,
//        141516, "first post", comment = comment, likes = likes, attachments = attachments
//    )

    @Before
    fun clearWallService() {
        WallService.clear()
    }

    @Test
    fun addPost_test() {
        attachments += attachment
        val post = Post(
            2, 12345, 54321, 2323, 4545,
            141516, "first post", comment = comment, likes = likes, attachments = attachments
        )
        val addedPost = addPost(post)
        assertEquals("audio", addedPost.attachments.last().type )
//        assertEquals(1, addedPost.id)
//        assertEquals("first post", addedPost.text)
    }

    @Test
    fun updatePost_testTrue() {
        attachments += attachment
        val post = Post(
            2, 12345, 54321, 2323, 4545,
            141516, "first post", comment = comment, likes = likes, attachments = attachments
        )
        addPost(post)
        val result = updatePost(post.copy(id = 1, text = "first updated post"))
        assertEquals(true, result)
        assertEquals("first updated post", WallService.getPosts().last().text)
    }

    @Test
    fun updatePost_testFalse() {
        attachments += attachment
        val post = Post(
            2, 12345, 54321, 2323, 4545,
            141516, "first post", comment = comment, likes = likes, attachments = attachments
        )
        addPost(post)
        val result = updatePost(post.copy(id = 2, text = "first updated post"))
        assertEquals(false, result)
    }
}