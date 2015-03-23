package com.squirrel.notesapp;

public class NavDrawerItem {
     
    private String title;
    private int icon;
    private String desc;
     
    public NavDrawerItem(){}
 
    public NavDrawerItem(String title, int icon, String desc){
        this.title = title;
        this.icon = icon;
        this.desc = desc;
    }
     
     
    public String getTitle(){
        return this.title;
    }
     
    public int getIcon(){
        return this.icon;
    }
     
    public String getDesc(){
        return this.desc;
    }
     
    public void setTitle(String title){
        this.title = title;
    }
     
    public void setIcon(int icon){
        this.icon = icon;
    }
     
    public void setDesc(String desc){
        this.desc = desc;
    }
}