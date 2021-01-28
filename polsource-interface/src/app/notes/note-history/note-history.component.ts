import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbAlert, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { NotesHistoryService } from '../services/notes-history.service';
import { Note } from '../shared/note.model';

@Component({
  selector: 'app-note-history',
  templateUrl: './note-history.component.html',
  styleUrls: ['./note-history.component.css']
})
export class NoteHistoryComponent implements OnInit {

  private alertSubject = new Subject<string>();
  public alertMessage = '';
  @ViewChild('selfCloseAlert', {static: false}) selfCloseAlert!: NgbAlert;

  public historyNotes!: Observable<Note[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private modalService: NgbModal,
              private notesHistoryService: NotesHistoryService) { }

  ngOnInit(): void {
    this.historyNotes = this.notesHistoryService.getAllNotesInThread(String(this.route.snapshot.paramMap.get('id')));
    this.alertSubject.subscribe(message => this.alertMessage = message);
    this.alertSubject.pipe(debounceTime(5000)).subscribe(() => {
      if(this.selfCloseAlert){
        this.selfCloseAlert.close();
      }
    }); 
  }

  public changeAlertMessageDelete(note: Note) { 
    this.alertSubject.next('Note ' + note.title + ' deleted succesfully.');
  }
  
  public changeAlertMessage(message: string) { 
    this.alertSubject.next(message);
  }

  deleteNote(note: Note){
    this.notesHistoryService.deleteNote(note.id).subscribe(
      response => {
        this.changeAlertMessageDelete(note);
        this.historyNotes = this.notesHistoryService.getAllNotesInThread(String(this.route.snapshot.paramMap.get('id')));
      },
      error => {
        this.changeAlertMessage('Something went wrong while deleting the note.');
      }
    );
  }

}
