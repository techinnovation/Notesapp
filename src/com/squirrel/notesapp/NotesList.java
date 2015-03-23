package com.squirrel.notesapp;

public class NotesList {
		private String NoteId;
		private String NoteTitle;
		private String NoteDate;
		
		public String getNoteId(){
			return this.NoteId;
		}
		public String getNoteTitle(){
			return this.NoteTitle;
		}
		public String getNoteDate(){
			return this.NoteDate;
		}
		public void setNoteId(String id){
	        this.NoteId = id;
	    }
		public void setNoteTitle(String title){
			this.NoteTitle = title;
		}
		public void setNoteDate(String date){
			this.NoteDate = date;
		}
	
}
