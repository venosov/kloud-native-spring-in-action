package com.polarbookshop.catalogservice.web

import com.polarbookshop.catalogservice.domain.Book
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester
import org.springframework.boot.test.json.ObjectContentAssert

@JsonTest
class BookJsonTests {
    @Autowired
    private lateinit var json: JacksonTester<Book>

    @Test
    fun testSerialize() {
        val book = Book("1234567890", "Title", "Author", 9.90)
        val jsonContent = json.write(book)
        Assertions.assertThat(jsonContent).extractingJsonPathStringValue("@.isbn")
            .isEqualTo(book.isbn)
        Assertions.assertThat(jsonContent).extractingJsonPathStringValue("@.title")
            .isEqualTo(book.title)
        Assertions.assertThat(jsonContent).extractingJsonPathStringValue("@.author")
            .isEqualTo(book.author)
        Assertions.assertThat(jsonContent).extractingJsonPathNumberValue("@.price")
            .isEqualTo(book.price)
    }

    @Test
    fun testDeserialize() {
        val content = """
          {
            "isbn": "1234567890",
            "title": "Title",
            "author": "Author",
            "price": 9.90
          }
          """
        Assertions.assertThat<ObjectContentAssert<Book>>(json.parse(content))
            .usingRecursiveComparison()
            .isEqualTo(Book("1234567890", "Title", "Author", 9.90))
    }
}
