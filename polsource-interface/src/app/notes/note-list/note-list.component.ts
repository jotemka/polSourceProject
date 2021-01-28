import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { NgbAlert, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { NotesService } from 'src/app/notes/services/notes.service';
import { NoteReadComponent } from '../note-read/note-read.component';
import { Note } from '../shared/note.model';

@Component({
  selector: 'app-note-list',
  templateUrl: './note-list.component.html',
  styleUrls: ['./note-list.component.css']
})
export class NoteListComponent implements OnInit {

  private alertSubject = new Subject<string>();
  public alertMessage = '';
  @ViewChild('selfCloseAlert', {static: false}) selfCloseAlert!: NgbAlert;

  public currentNotes!: Observable<Note[]>;
  // public currentNotes: Array<Note> = new Array<Note>();

  constructor(private router: Router,
              private modalService: NgbModal,
              private notesService: NotesService) { }

  ngOnInit(): void {
    // this.getAllCurrentNotes();
    this.currentNotes = this.notesService.getAllCurrentNotes();
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

  readNote(note: Note) {
    const modalRef = this.modalService.open(NoteReadComponent);
    modalRef.componentInstance.setNote(note);
  }

  deleteNote(note: Note){
    this.notesService.deleteNote(note.id).subscribe(
      response => {
        this.changeAlertMessageDelete(note);
        this.currentNotes = this.notesService.getAllCurrentNotes();
      },
      error => {
        this.changeAlertMessage('Something went wrong while deleting the note.');
      }
    );
  }

  // getAllCurrentNotes() {
  //   this.notesService.getAllCurrentNotes().subscribe(
  //     data => {
  //       this.currentNotes = data;
  //     },
  //     error => {
  //       console.log("something went wrong");        
  //     });
  // }

}
