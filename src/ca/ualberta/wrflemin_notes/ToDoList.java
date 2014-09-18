package ca.ualberta.wrflemin_notes;

import java.util.ArrayList;

public class ToDoList {

	private ArrayList<ToDoItem> toDoItemList;
	private int checkedItems;
	
	public ToDoList() {
		this.toDoItemList = new ArrayList<ToDoItem>();
		this.checkedItems = 0;
	}
	
	public int getItemCount(){
		return toDoItemList.size();
	}
	
	public void add(ToDoItem toDoItem){
		toDoItemList.add(toDoItem);
	}
	
	public ArrayList<ToDoItem> getToDoList(){
			return toDoItemList;
	}
	
	public int getCheckedCount(){
		return checkedItems;
	}
	public int getUncheckedCount(){
		return toDoItemList.size() - checkedItems;	
	}
	public void increaseCheckedCount(){
		checkedItems += 1;
	}
	public void decreaseCheckedCount(){
		checkedItems -= 1;
	}

}
