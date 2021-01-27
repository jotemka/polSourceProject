import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Note } from '../shared/note.model';

@Component({
  selector: 'app-note-read',
  templateUrl: './note-read.component.html',
  styleUrls: ['./note-read.component.css']
})
export class NoteReadComponent implements OnInit {

  public note!: Note;

  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

  setNote(noteToRead: Note){
    this.note = noteToRead;
  }

  close(){
    this.activeModal.close();
  }

}
