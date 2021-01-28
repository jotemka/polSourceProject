import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NoteCreateComponent } from './note-create/note-create.component';
import { NoteListComponent, SortableHeader } from './note-list/note-list.component';
import { AppRoutingModule } from '../app-routing.module';
import { RouterModule } from '@angular/router';
import { NoteReadComponent } from './note-read/note-read.component';
import { NoteEditComponent } from './note-edit/note-edit.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NoteHistoryComponent } from './note-history/note-history.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';



@NgModule({
  declarations: [
    NoteListComponent,
    NoteCreateComponent,
    NoteReadComponent,
    NoteEditComponent,
    NoteHistoryComponent,
    SortableHeader,
    PageNotFoundComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    NgbModule
  ]
})
export class NotesModule { }
