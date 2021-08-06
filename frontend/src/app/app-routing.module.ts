import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContentComponent } from './content/content.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { AddBookComponent } from './add-book/add-book.component';
import { DetailsComponent } from './details/details.component';

const routes: Routes = [
  { path: 'books/add', component: AddBookComponent },
  { path: 'books/:id', component: DetailsComponent },
  { path: 'books', component: ContentComponent },
  { path: '', component: LandingPageComponent },
  { path: '404', component: PageNotFoundComponent },
  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
