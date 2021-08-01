package com.example.librarybackend.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
@Table(name = "book")
public class Book {

    @ApiModelProperty(notes = "Unique ID of the book in database", name = "id", required = true, value = "id")
    @Column(unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 100, message = "Name of the author cannot exceed 100 characters")
    private String author;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 100, message = "Book's title cannot exceed 100 characters")
    private String title;

    private long price;

    @Size(max = 5000, message = "Book's description cannot exceed 5000 characters")
    private String description;

    @Column(unique = true)
    @Size(min = 13, max = 13, message = "EAN must be exactly 13 characters long")
    private String ean;

    public Book() {

    }

    public Book(long id, String author, String title, long price, String description, String ean) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.price = price;
        this.description = description;
        this.ean = ean;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, price, description, ean);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!(object instanceof Book)) {
            return false;
        }
        Book that = (Book) object;
        return this.id == that.getId() &&
                this.author.equals(that.getAuthor()) &&
                this.title.equals(that.getTitle()) &&
                this.price == that.getPrice() &&
                this.description.equals(that.getDescription()) &&
                this.ean.equals(that.getEan());
    }
}
