import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NoteCreateComponent } from './notes/note-create/note-create.component';
import { NoteEditComponent } from './notes/note-edit/note-edit.component';
import { NoteHistoryComponent } from './notes/note-history/note-history.component';
import { NoteListComponent } from './notes/note-list/note-list.component';
import { PageNotFoundComponent } from './notes/page-not-found/page-not-found.component';

const routes: Routes = [
  { path: '', redirectTo: '/current-notes', pathMatch: 'full'},
  { path: 'current-notes', component: NoteListComponent },
  { path: 'new-note', component: NoteCreateComponent },
  { path: 'edit-note/:id', component: NoteEditComponent },
  { path: 'note-history/:id', component: NoteHistoryComponent },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
