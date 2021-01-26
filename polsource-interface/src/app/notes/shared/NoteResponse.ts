import { Note } from "./note.model";

export class NoteResponse {
  notification!: string;
  status!: string;
  data!: Array<Note>;
}