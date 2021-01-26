import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { catchError, tap, map } from 'rxjs/operators';
// import { NoteAPIResponse } from '../shared/note-apiresponse';
// import { Note } from '../shared/note.model';
// import { NewNote } from '../shared/new-note.model';
import { Observable } from 'rxjs';
import { NewNote } from '../shared/new-note.model';
import { Note } from '../shared/note.model';
import { NoteAPIResponse } from '../shared/note-apiresponse';
 
@Injectable({
  providedIn: 'root'
})
export class NotesService {

  rootUrl: string;
  // header: HttpHeaders = {};

  constructor(private http: HttpClient) { 
    this.rootUrl = 'http://localhost:8080/notes';
  }

  createNote(newNote: NewNote){
    return this.http.post(this.rootUrl+ '/new-note', newNote, { observe: 'response'});
  }

  getAllCurrentNotes(): Observable<Note[]>{
    return this.http.get<NoteAPIResponse>(this.rootUrl + '/all-current-notes').pipe(map(res => res.data));
  }

  updateNote(note: Note){
    return this.http.post(this.rootUrl + '/updated-note', note, { observe: 'response' });
  }

  deleteNote(id: number){
    return this.http.delete(this.rootUrl + '/' + id, {observe: 'response'});
  }
}
