package ru.netology

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import ru.netology.WallService.addPost
import ru.netology.WallService.updatePost

class WallServiceTest {

    val likes = Likes(5, true)
    val comment = Comment(1)
    val post = Post(2, 12345, 54321, 2323, 4545,
        141516, "first post", comment = comment, likes = likes)

    @Before
    fun clearWallService() {
        WallService.clear()
    }

    @Test
    fun addPost_test() {
        val addedPost = addPost(post)
        assertEquals(1, addedPost.id)
        assertEquals("first post", addedPost.text)
    }

    @Test
    fun updatePost_testTrue() {
        addPost(post)
        val result = updatePost(post.copy(id = 1, text = "first updated post"))
        assertEquals(true, result)
        assertEquals("first updated post", WallService.getPosts().last().text)
    }

    @Test
    fun updatePost_testFalse() {
        addPost(post)
        val result = updatePost(post.copy(id = 2, text = "first updated post"))
        assertEquals(false, result)
    }
}