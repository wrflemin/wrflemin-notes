package com.example.wrflemin_notes;

import java.util.ArrayList;

public class ToDoList {

	private ArrayList<ToDoItem> toDoItemList;
	
	public ToDoList() {
		this.toDoItemList = new ArrayList<ToDoItem>();
	}
	
	public int getItemCount(){
		return toDoItemList.size();
	}
	
	public void add(ToDoItem toDoItem){
		toDoItemList.add(toDoItem);
	}

}
