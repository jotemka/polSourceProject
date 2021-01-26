import { Note } from "./note.model";

export interface NoteAPIResponse {
  notification: string;
  status: string;
  data: Note[];
}
