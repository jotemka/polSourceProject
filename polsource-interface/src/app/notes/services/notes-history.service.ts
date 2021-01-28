import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { NoteAPIResponse } from '../shared/note-apiresponse';
import { Note } from '../shared/note.model';

@Injectable({
  providedIn: 'root'
})
export class NotesHistoryService {

  rootUrl: string;

  constructor(private http: HttpClient) {
    this.rootUrl = 'http://localhost:8080/notes-history';
  }

  // Getting NoteAPIResponse for easier extraction of data from ResponseObject sent from backend
  getAllNotesInThread(threadId: string): Observable<Note[]>{
    return this.http.get<NoteAPIResponse>(this.rootUrl + '/all-notes-in-thread/' + threadId).pipe(map(res => res.data));
  }

  deleteNote(id: number){
    return this.http.delete(this.rootUrl + '/' + id, {observe: 'response'});
  }
}
