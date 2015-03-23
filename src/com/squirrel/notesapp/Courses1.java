package com.squirrel.notesapp;



public class Courses1 {
	private String CourseCode;
    private String CourseTitle;
    private String Id;
    private String LecturerName;
         
     
    public String getCourseCode(){
        return this.CourseCode;
    }
    public String getCourseTitle(){
    	return this.CourseTitle;
    }
    public String getCourseID(){
    	return this.Id;
    }
    public String getLecturerName(){
    	return this.LecturerName;
    }
    
    public void setCourseCode(String code){
        this.CourseCode = code;
    }
    public void setCourseTitle(String title){
    	this.CourseTitle = title;
    }
    public void setID(String id){
    	this.Id = id;
    }
    public void setLecturerName(String lecturer){
    	this.LecturerName = lecturer;
    }
    

}
