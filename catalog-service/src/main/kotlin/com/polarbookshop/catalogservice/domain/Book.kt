package com.polarbookshop.catalogservice.domain

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Positive

@Suppress("unused")
data class Book(@field:NotBlank(message = "The book ISBN must be defined.")
                @field:Pattern(regexp = "^(\\d{10}|\\d{13})$",
                    message = "The ISBN format must be valid.")
                val isbn: String,
                @field:NotBlank(message = "The book title must be defined.")
                val title: String,
                @field:NotBlank(message = "The book author must be defined.")
                val author: String,
                @field:Positive(message = "The book price must be greater than zero.")
                val price: Double)
