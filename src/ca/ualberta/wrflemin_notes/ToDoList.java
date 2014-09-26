package ca.ualberta.wrflemin_notes;

import java.util.ArrayList;

public class ToDoList {

	private ArrayList<ToDoItem> toDoItemList;
	
	public ToDoList() {
		this.toDoItemList = new ArrayList<ToDoItem>();
	}
	
	public int getItemCount(){
		return toDoItemList.size();
	}
	
	public void setList(ArrayList<ToDoItem> toDoList){
		toDoItemList = toDoList;
	}
	
	public void add(ToDoItem toDoItem){
		//adds the item to the first entry of the list
		//to keep newer list items on top of the view.
		toDoItemList.add(0, toDoItem);
	}
	
	public void delete(int position){
		toDoItemList.remove(position);
	}
	
	public ToDoItem getToDo(int position){
		return toDoItemList.get(position);
	}
	
	public ArrayList<ToDoItem> getToDoList(){
			return toDoItemList;
	}
	
	public int getCheckedCount(){
		int checkedItems = 0;
		//counts the number of checked todo items
		for (int i = 0; i<toDoItemList.size(); i++){
			if (toDoItemList.get(i).checkedStatus())
				checkedItems++;
		}
		return checkedItems;
	}
	public int getUncheckedCount(){
		int unchecked = 0;
		//counts number of todos that are unchecked
		for (int i = 0; i<toDoItemList.size(); i++){
			if (!toDoItemList.get(i).checkedStatus()){
				unchecked++;
			}
		}
		
		return unchecked;
	}
}
