package com.polarbookshop.catalogservice.domain

interface BookRepository {
    fun findAll(): Iterable<Book>
    fun findByIsbn(isbn: String): Book?
    fun existsByIsbn(isbn: String): Boolean
    fun save(book: Book): Book
    fun deleteByIsbn(isbn: String)
}
