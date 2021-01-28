import { Note } from "./note.model";
export interface SingleNoteAPIResponse {
  notification: string;
  status: string;
  data: Note;
  }
