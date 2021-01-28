import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { map, switchMap, tap } from 'rxjs/operators';
import { NotesService } from '../services/notes.service';
import { NoteAPIResponse } from '../shared/note-apiresponse';
import { Note } from '../shared/note.model';

@Component({
  selector: 'app-note-edit',
  templateUrl: './note-edit.component.html',
  styleUrls: ['./note-edit.component.css']
})
export class NoteEditComponent implements OnInit {

  public maxLength: number = 500;
  public editNoteId: number = 0;

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
      // Validators.maxLength(500)
    ])
  });

  constructor(private router: Router,
              private route: ActivatedRoute,
              private notesService: NotesService) { }

  ngOnInit(): void {
    console.log("xxx");
    // this.editedNoteObservable = this.route.paramMap.pipe(
    //   switchMap((params: ParamMap) => this.notesService.getNote(Number(params.get('id')))
    //   ));

    // this.editedNoteObservable = this.notesService.getNote(Number(this.route.snapshot.paramMap.get('id')));
    this.notesService.getNote(Number(this.route.snapshot.paramMap.get('id'))).subscribe(
      data => {
        this.editedNote = data;
        this.editNoteForm.get('title')?.patchValue(this.editedNote.title);
        this.editNoteForm.get('content')?.patchValue(this.editedNote.content);
      }
    );


    
    // this.editedNoteObservable = this.route.params.subscribe( params => {

    // })
      
    // console.log(this.editedNoteObservable);
    // this.editedNoteObservable.subscribe(x => {
    //   this.editedNote = x;
    //   console.log(this.editedNote);
      
    // })

    // this.route.params.subscribe( params => {
    //   this.notesService.getNote(params['id']).subscribe(
    //     data => {
    //       this.responseData = data.body as NoteAPIResponse;
    //       this.editedNote = this.responseData.data[0];

    //     }, 
    //     error => {
    //       alert("error");
    //     }
    //   )
    // }
    //   // switchMap((params: ParamMap) => this.notesService.getNote(Number(params.get('id'))).subscribe(
    //   //   data => {

    //   //   }
    //   // ))
    // )
    
    this.editNoteForm.get('content')?.valueChanges.subscribe(val => {
      this.charactersLeft = this.maxLength - val.length
    });

    // this.editNoteForm.patchValue({
    //   title: this.editedNote.title,
    //   content: this.editedNote.content
    // })
    // this.editedNoteObservable.pipe(map(x => {
    //   console.log(x);
    //   this.editedNote.id = x.id;
    //   this.editedNote.title = x.title;
    //   this.editedNote.content = x.content;
    //   this.editedNote.created = x.created;
    //   this.editedNote.modified = x.modified;
    //   this.editedNote.thread_id = x.thread_id;
    //   this.editedNote.version = x.version;
    // }))

    // this.editNoteForm.patchValue({
    //   title: this.editedNote.title,
    //   content: this.editedNote.content
    // })
      // this.editNoteId = this.route.snapshot.paramMap.get('id');
    // this.notesService.getNote(id)
  }

  onSubmit(){
    this.editedNote.title = this.editNoteForm.get('title')?.value;
    this.editedNote.content = this.editNoteForm.get('content')?.value;
    this.notesService.updateNote(this.editedNote).subscribe(
      response => {
        this.router.navigate(['/current-notes']);
      },
      error => {
        alert("Something went wrong.");
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
