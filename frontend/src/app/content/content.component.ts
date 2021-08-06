import { Component, OnInit } from '@angular/core';
import { BookService } from '../book.service';
import { Book } from '../book';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css'],
})
export class ContentComponent implements OnInit {
  books: Book[] = [];
  constructor(private bookService: BookService) {}

  ngOnInit(): void {
    this.getBooks();
  }

  getBooks(): void {
    this.bookService.getAllBooks().subscribe((books) => {
      this.books = books;
    });
  }
}
