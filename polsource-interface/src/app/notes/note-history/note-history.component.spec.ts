import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NoteHistoryComponent } from './note-history.component';

describe('NoteHistoryComponent', () => {
  let component: NoteHistoryComponent;
  let fixture: ComponentFixture<NoteHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NoteHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NoteHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
