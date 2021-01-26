import { TestBed } from '@angular/core/testing';

import { NotesHistoryService } from './notes-history.service';

describe('NotesHistoryService', () => {
  let service: NotesHistoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NotesHistoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
