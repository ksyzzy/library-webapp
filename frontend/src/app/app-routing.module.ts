import { NgModule } from '@angular/core';
import { RouterModule, Routes  } from '@angular/router'; 
import { ContentComponent } from './content/content.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { LandingPageComponent } from './landing-page/landing-page.component';

const routes: Routes = [
  {path: 'books', component: ContentComponent},
  {path: '', component: LandingPageComponent},
  {path: '**', component: PageNotFoundComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
