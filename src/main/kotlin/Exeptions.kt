package ru.netology

class PostNotFoundException(message: String) : RuntimeException(message)

class CommentNotFoundException(message: String) : RuntimeException(message)

class ReasonNotFoundException(message: String) : RuntimeException(message)

class NoteNotFoundException(message: String) : Exception(message)