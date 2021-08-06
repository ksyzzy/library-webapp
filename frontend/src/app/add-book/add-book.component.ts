import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from '../book';
import { BookService } from '../book.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css'],
})
export class AddBookComponent implements OnInit {
  book: Book = {
    id: 0,
    author: '',
    title: '',
    description: '',
    price: 0,
    ean: '',
  };

  constructor(private bookService: BookService, private router: Router) {}

  ngOnInit(): void {}

  updateAuthor(author: string) {
    this.book!.author = author;
  }

  updateTitle(title: string) {
    this.book!.title = title;
  }

  updateDescription(description: string) {
    this.book!.description = description;
  }

  updatePrice(price: number) {
    this.book!.price = price;
  }

  updateEAN(ean: string) {
    this.book!.ean = ean;
  }

  addBook() {
    let json = {
      id: this.book?.id,
      author: this.book?.author,
      title: this.book?.title,
      description: this.book.description === '' ? ' ' : this.book.description,
      price: this.book?.price,
      ean: this.book?.ean,
    };
    this.bookService.addBook(json).subscribe(
      (data) => {
        alert('Book added successfully');
        this.router
          .navigateByUrl('/', { skipLocationChange: true })
          .then(() => this.router.navigate(['books']));
      },
      (error) => {
        alert('Please provide valid data');
      }
    );
  }
}
