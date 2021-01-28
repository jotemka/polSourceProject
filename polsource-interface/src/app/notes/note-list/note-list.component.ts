import { Component, Directive, EventEmitter, Input, OnInit, Output, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { Router } from '@angular/router';
import { NgbAlert, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { NotesService } from 'src/app/notes/services/notes.service';
import { NoteReadComponent } from '../note-read/note-read.component';
import { Note } from '../shared/note.model';

export type SortColumn = keyof Note | '';
export type SortDirection = 'asc' | 'desc';
const rotate: {[key: string]: SortDirection} = {
  'asc':'desc', 'desc':'asc'
};

const compare = (v1: string | number | boolean, v2: string | number | boolean) => 
v1 < v2 ? -1: v1 > v2 ? 1 : 0;

export interface SortEvent{
  column: SortColumn;
  direction: SortDirection;
}

@Directive({
  selector: 'th[sortable]',
  host: {
    '[class.asc]': 'direction === "asc"',
    '[class.desc]': 'direction === "desc"',
    '(click)': 'rotate()'
  }
})
export class SortableHeader {

  @Input() sortable: SortColumn = '';
  @Input() direction: SortDirection = 'asc';
  @Output() sort = new EventEmitter<SortEvent>()

  rotate() {
    this.direction = rotate[this.direction];
    this.sort.emit({column: this.sortable, direction: this.direction});
  }
}


@Component({
  selector: 'app-note-list',
  templateUrl: './note-list.component.html',
  styleUrls: ['./note-list.component.css']
})
export class NoteListComponent implements OnInit {

  @ViewChildren(SortableHeader) headers!: QueryList<SortableHeader>;

  private alertSubject = new Subject<string>();
  public alertMessage = '';
  @ViewChild('selfCloseAlert', {static: false}) selfCloseAlert!: NgbAlert;

  public currentNotes!: Note[];
  // public currentNotes!: Observable<Note[]>;
  // public currentNotes: Array<Note> = new Array<Note>();

  constructor(private router: Router,
              private modalService: NgbModal,
              private notesService: NotesService) { }

  ngOnInit(): void {
    // this.getAllCurrentNotes();
    // this.currentNotes = this.notesService.getAllCurrentNotes();
    this.notesService.getAllCurrentNotes().subscribe(
      data => {
        this.currentNotes = data;
      }
    );
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
        // this.currentNotes = this.notesService.getAllCurrentNotes();
        this.notesService.getAllCurrentNotes().subscribe(
          data => {
            this.currentNotes = data;
          }
        );
      },
      error => {
        this.changeAlertMessage('Something went wrong while deleting the note.');
      }
    );
  }

  onSort({column, direction}: SortEvent){
    
    
    // this.headers.forEach(header => {
    //   if(header.sortable !== column) {
    //     // header.direction = ''; tu nie moge tego zrobic bo nie mam neutralnego kierunku
    //     console.log("costam");
        
    //   }
    // });

    if(column === '') {
      // tu by bylo przypisanie z consta 
      console.log("hehe");
    } else {
      console.log("xaxa")
      // this.currentNotes.subscribe(
      //   data => {
      //     [...data].sort((a,b) => {
      //       const res = compare(a[column], b[column]);
      //       return direction === 'asc' ? res : -res;
      //     })
      //   }
      // )
      this.currentNotes = [...this.currentNotes].sort((a, b) => {
        const res = compare(a[column], b[column]);
        return direction === 'asc' ? res : -res;
      })
    }
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
