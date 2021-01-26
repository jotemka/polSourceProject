import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NoteListComponent } from './notes/note-list/note-list.component';

const routes: Routes = [
  { path: '', redirectTo: '/current-notes', pathMatch: 'full'},
  { path: 'current-notes', component: NoteListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
