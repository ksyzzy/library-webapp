package com.example.librarybackend.repositories;

import com.example.librarybackend.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Modifying
    @Query("UPDATE Book b SET b.author = :author, b.title = :title, b.description = :description, b.ean = :ean, b.price = :price " +
            "WHERE b.id = :id")
    @Transactional
    void updateBook(@Param("id") long id, @Param("author") String author, @Param("title") String title,
                    @Param("description") String description, @Param("ean") String ean, @Param("price") long price);
}
