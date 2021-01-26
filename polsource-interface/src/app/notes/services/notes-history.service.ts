import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NotesHistoryService {

  rootUrl: string;

  constructor(private http: HttpClient) {
    this.rootUrl = 'http://localhost:8080/notes-history';
  }

  getAllNotesInThread(threadId: string){
    return this.http.get(this.rootUrl + '/all-notes-in-thread/' + threadId);
  }

  deleteNote(id: number){
    return this.http.delete(this.rootUrl + '/' + id, {observe: 'response'});
  }
}
