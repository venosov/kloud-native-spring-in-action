package com.polarbookshop.catalogservice.persistence

import com.polarbookshop.catalogservice.domain.Book
import com.polarbookshop.catalogservice.domain.BookRepository
import org.springframework.stereotype.Repository

@Repository
class InMemoryBookRepository : BookRepository {
    override fun findAll(): Iterable<Book> {
        return books.values
    }

    override fun findByIsbn(isbn: String): Book? {
        return if (existsByIsbn(isbn)) books[isbn] else null
    }

    override fun existsByIsbn(isbn: String): Boolean {
        return books[isbn] != null
    }

    override fun save(book: Book): Book {
        books[book.isbn] = book
        return book
    }

    override fun deleteByIsbn(isbn: String) {
        books.remove(isbn)
    }

    companion object {
        private val books: MutableMap<String, Book> = mutableMapOf()
    }
}
