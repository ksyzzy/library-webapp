import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Book } from './book';

@Injectable({
  providedIn: 'root',
})
export class BookService {
  booksUrl = 'http://localhost:8080/books';
  constructor(private http: HttpClient, private router: Router) {}

  getAllBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(this.booksUrl);
  }

  getBookById(id: string): Observable<Book> {
    return this.http.get<Book>(this.booksUrl + '/' + id).pipe(
      catchError((err) => {
        if (err instanceof HttpErrorResponse && err.status !== 200) {
          this.router.navigate(['404']);
        }
        return throwError(err);
      })
    );
  }

  addBook(data: {}) {
    return this.http.post<Book>(this.booksUrl, data);
  }

  updateBook(id: string, data: {}) {
    this.http.put<Book>(this.booksUrl + '/' + id, data).subscribe(
      (data) => alert('Book updated successfully'),
      (error) => {
        alert('Please provide valid data');
      }
    );
  }

  deleteBookById(id: string) {
    return this.http.delete(this.booksUrl + '/' + id);
  }
}
