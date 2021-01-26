import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NotesService } from './notes/services/notes.service';
import { NotesHistoryService } from './notes/services/notes-history.service';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NoteListComponent } from './notes/note-list/note-list.component';

@NgModule({
  declarations: [
    AppComponent,
    NoteListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [
    NotesService,
    NotesHistoryService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
