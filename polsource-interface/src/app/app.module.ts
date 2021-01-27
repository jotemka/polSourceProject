import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NotesService } from './notes/services/notes.service';
import { NotesHistoryService } from './notes/services/notes-history.service';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NoteListComponent } from './notes/note-list/note-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NoteCreateComponent } from './notes/note-create/note-create.component';
import { NotesModule } from './notes/notes.module';

@NgModule({
  declarations: [
    AppComponent,
    // NoteListComponent,
    // NoteCreateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    NotesModule,
    // FormsModule,
    // ReactiveFormsModule
  ],
  providers: [
    NotesService,
    NotesHistoryService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
