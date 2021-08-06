import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from '../book';
import { BookService } from '../book.service';
import { Observable } from 'rxjs';
import { map, catchError, defaultIfEmpty } from 'rxjs/operators';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css'],
})
export class DetailsComponent implements OnInit {
  id: string;
  book$: Observable<Book>;
  book: Book | undefined;

  constructor(
    private route: ActivatedRoute,
    private bookService: BookService,
    private router: Router
  ) {
    this.id = route.snapshot.params.id;
    this.book$ = this.bookService.getBookById(this.id);
    this.book$.subscribe((value) => (this.book = value));
  }

  ngOnInit(): void {}

  forceRedirect(): void {
    this.router.navigate(['404']);
  }

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

  updateBook() {
    if (
      confirm(
        'Are you sure you want to update this book in database with provided values?'
      )
    ) {
      let json = {
        id: this.book?.id,
        author: this.book?.author,
        title: this.book?.title,
        description: this.book?.description,
        price: this.book?.price,
        ean: this.book?.ean,
      };
      this.bookService.updateBook(this.id, json);
    }
  }

  deleteBook() {
    if (confirm('Are you sure you want to delete this book from database?')) {
      this.bookService.deleteBookById(this.id).subscribe(
        (data) => alert('Book deleted successfully'),
        (error) => alert('A problem has occurred while deleting book')
      );
      this.router
        .navigateByUrl('/', { skipLocationChange: true })
        .then(() => this.router.navigate(['books']));
    }
  }
}
