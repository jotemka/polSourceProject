import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { NotesService } from 'src/app/notes/services/notes.service';
import { NoteReadComponent } from '../note-read/note-read.component';
import { Note } from '../shared/note.model';

@Component({
  selector: 'app-note-list',
  templateUrl: './note-list.component.html',
  styleUrls: ['./note-list.component.css']
})
export class NoteListComponent implements OnInit {

  public currentNotes!: Observable<Note[]>;
  // public currentNotes: Array<Note> = new Array<Note>();

  constructor(private router: Router,
              private modalService: NgbModal,
              private notesService: NotesService) { }

  ngOnInit(): void {
    // this.getAllCurrentNotes();
    this.currentNotes = this.notesService.getAllCurrentNotes(); 
  }

  readNote(note: Note) {
    const modalRef = this.modalService.open(NoteReadComponent);
    modalRef.componentInstance.setNote(note);
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
