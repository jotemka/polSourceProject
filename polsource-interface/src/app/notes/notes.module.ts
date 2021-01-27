import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NoteCreateComponent } from './note-create/note-create.component';
import { NoteListComponent } from './note-list/note-list.component';
import { AppRoutingModule } from '../app-routing.module';
import { RouterModule } from '@angular/router';
import { NoteReadComponent } from './note-read/note-read.component';
import { NoteEditComponent } from './note-edit/note-edit.component';



@NgModule({
  declarations: [
    NoteListComponent,
    NoteCreateComponent,
    NoteReadComponent,
    NoteEditComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule
  ]
})
export class NotesModule { }
