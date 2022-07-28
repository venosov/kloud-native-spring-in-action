package com.polarbookshop.catalogservice.web

import com.polarbookshop.catalogservice.domain.Book
import com.polarbookshop.catalogservice.domain.BookService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("books")
class BookController(private val bookService: BookService) {
    @GetMapping
    fun get(): Iterable<Book> {
        return bookService.viewBookList()
    }

    @GetMapping("{isbn}")
    fun getByIsbn(@PathVariable isbn: String): Book {
        return bookService.viewBookDetails(isbn)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun post(@Valid @RequestBody book: Book): Book {
        return bookService.addBookToCatalog(book)
    }

    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable isbn: String) {
        bookService.removeBookFromCatalog(isbn)
    }

    @PutMapping("{isbn}")
    fun put(@PathVariable isbn: String, @Valid @RequestBody book: Book): Book {
        return bookService.editBookDetails(isbn, book)
    }
}
