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
  
  private alertSubject = new Subject<string>();
  public alertMessage = '';
  @ViewChild('selfCloseAlert', {static: false}) selfCloseAlert!: NgbAlert;

  public maxLength: number = 500;
  
  public newNote: NewNote = new NewNote();
  public charactersLeft!: number;

  newNoteForm = new FormGroup({
    title: new FormControl('', [
      Validators.required
    ]),
    content: new FormControl('', [
      Validators.required
    ])
  });

  constructor(private router: Router,
              private notesService: NotesService) { }

  ngOnInit(): void {
    this.newNoteForm.get('content')?.valueChanges.subscribe(val => {
      this.charactersLeft = this.maxLength - val.length
    })
    this.alertSubject.subscribe(message => this.alertMessage = message);
    this.alertSubject.pipe(debounceTime(5000)).subscribe(() => {
      if(this.selfCloseAlert){
        this.selfCloseAlert.close();
      }
    });
    
  }

  public changeAlertMessage(message: string) { 
    this.alertSubject.next(message);
  }

  onSubmit(){
    // taking values from form
    this.newNote.title = this.newNoteForm.get('title')?.value;
    this.newNote.content = this.newNoteForm.get('content')?.value;
    this.notesService.createNote(this.newNote).subscribe(
      response => {
        this.router.navigate(['/current-notes']);
      },
      error => {
        // alert("Something went wrong.");
        this.changeAlertMessage("Something went wrong.");
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
