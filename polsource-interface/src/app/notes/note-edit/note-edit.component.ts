import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { NgbAlert } from '@ng-bootstrap/ng-bootstrap';
import { Observable, Subject } from 'rxjs';
import { debounceTime, map, switchMap, tap } from 'rxjs/operators';
import { NotesService } from '../services/notes.service';
import { NoteAPIResponse } from '../shared/note-apiresponse';
import { Note } from '../shared/note.model';

@Component({
  selector: 'app-note-edit',
  templateUrl: './note-edit.component.html',
  styleUrls: ['./note-edit.component.css']
})
export class NoteEditComponent implements OnInit {

  private alertSubject = new Subject<string>();
  public alertMessage = '';
  @ViewChild('selfCloseAlert', {static: false}) selfCloseAlert!: NgbAlert;

  public maxLength: number = 500;

  public editedNoteObservable!: Observable<Note>;
  public editedNote: Note = new Note();
  public responseData!: NoteAPIResponse;
  public charactersLeft!: number;

  editNoteForm = new FormGroup({
    title: new FormControl('', [
      Validators.required
    ]),
    content: new FormControl('', [
      Validators.required
    ])
  });

  constructor(private router: Router,
              private route: ActivatedRoute,
              private notesService: NotesService) { }

  ngOnInit(): void {
    //retrieving a note from backend and filling the form with data from it
    this.notesService.getNote(Number(this.route.snapshot.paramMap.get('id'))).subscribe(
      data => {
        this.editedNote = data;
        this.editNoteForm.get('title')?.patchValue(this.editedNote.title);
        this.editNoteForm.get('content')?.patchValue(this.editedNote.content);
      }
    );
    this.alertSubject.subscribe(message => this.alertMessage = message);
    this.alertSubject.pipe(debounceTime(5000)).subscribe(() => {
      if(this.selfCloseAlert){
        this.selfCloseAlert.close();
      }
    });
    this.editNoteForm.get('content')?.valueChanges.subscribe(val => {
      this.charactersLeft = this.maxLength - val.length
    });

  }

  public changeAlertMessage(message: string) { 
    this.alertSubject.next(message);
  }

  onSubmit(){
    this.editedNote.title = this.editNoteForm.get('title')?.value;
    this.editedNote.content = this.editNoteForm.get('content')?.value;
    this.notesService.updateNote(this.editedNote).subscribe(
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
    return this.editNoteForm.get("title");
  }

  get content(){
    return this.editNoteForm.get("content");
  }

}
