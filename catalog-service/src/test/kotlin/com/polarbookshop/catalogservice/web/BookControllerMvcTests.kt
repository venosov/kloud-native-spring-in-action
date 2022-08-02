package com.polarbookshop.catalogservice.web

import com.polarbookshop.catalogservice.domain.BookNotFoundException
import com.polarbookshop.catalogservice.domain.BookService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(BookController::class)
internal class BookControllerMvcTests() {
    @Autowired
    private lateinit var mockMvc: MockMvc
    @MockBean
    private lateinit var bookService: BookService
    @Test
    @Throws(Exception::class)
    fun whenGetBookNotExistingThenShouldReturn404() {
        val isbn = "73737313940"
        BDDMockito.given(bookService!!.viewBookDetails(isbn))
            .willThrow(BookNotFoundException::class.java)
        mockMvc
            .perform(MockMvcRequestBuilders.get("/books/$isbn"))
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }
}
