import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbAlert } from '@ng-bootstrap/ng-bootstrap';
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { NotesService } from '../services/notes.service';
import { NewNote } from '../shared/new-note.model';

@Component({
  selector: 'app-note-create',
  templateUrl: './note-create.component.html',
  styleUrls: ['./note-create.component.css']
})
export class NoteCreateComponent implements OnInit {
  
  public maxLength: number = 500;
  
  public newNote: NewNote = new NewNote();
  public charactersLeft!: number;

  newNoteForm = new FormGroup({
    title: new FormControl('', [
      Validators.required
    ]),
    content: new FormControl('', [
      Validators.required
      // Validators.maxLength(500)
    ])
  });

  // name = new FormControl('');

  constructor(private router: Router,
              private notesService: NotesService) { }

  ngOnInit(): void {
    this.newNoteForm.get('content')?.valueChanges.subscribe(val => {
      this.charactersLeft = this.maxLength - val.length
    })
  }

  onSubmit(){
    console.log("in submit method");
    this.newNote.title = this.newNoteForm.get('title')?.value;
    console.log(this.newNote.title);
    this.newNote.content = this.newNoteForm.get('content')?.value;
    console.log(this.newNote.content);
    this.notesService.createNote(this.newNote).subscribe(
      response => {
        this.router.navigate(['/current-notes']);
      },
      error => {
        alert("Something went wrong.");
      }
    )
  }

  get title(){
    return this.newNoteForm.get("title");
  }

  get content(){
    return this.newNoteForm.get("content");
  }
 
}
