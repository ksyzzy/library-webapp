package com.example.librarybackend.models;

import com.example.librarybackend.validators.ValidEAN;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;

@AllArgsConstructor
@ApiModel(description = "Book model that is stored in database")
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "book")
@ToString
public class Book {

    @ApiModelProperty(value = "Unique ID of the book in database", name = "id", example = "1")
    @Column(unique = true, nullable = false)
    @Id
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ApiModelProperty(value = "Author name of the book", required = true, name = "author", example = "Richard L. Jackson")
    @Column(nullable = false)
    @NotBlank(message = "Author cannot be blank")
    @Size(max = 100, message = "Name of the author cannot exceed 100 characters")
    private String author;

    @ApiModelProperty(value = "Title of the book", required = true, name = "title", example = "Mysterious incident at the Devonshire's mansion")
    @Column(nullable = false)
    @NotBlank(message = "Book's title cannot be blank")
    @Size(max = 100, message = "Book's title cannot exceed 100 characters")
    private String title;

    @ApiModelProperty(value = "Price of the book in $", name = "price", example = "23")
    private long price;

    @ApiModelProperty(value = "Description of the book", name = "description", example = "A story about people and their deepest secrets")
    @Size(min = 1, max = 5000, message = "Book's description cannot exceed 5000 characters")
    private String description;

    @ApiModelProperty(value = "Unique European Article Number of the book", required = true, name = "ean", example = "5901234123457")
    @Column(unique = true, nullable = false)
    @ValidEAN
    private String ean;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }
}
