package ca.ualberta.wrflemin_notes;

public class ToDoItem {
	private String toDoText;
	private Boolean checked;
	
	public ToDoItem(String toDoText, boolean checked) {
		super();
		this.toDoText = toDoText;
		this.checked = checked;
	}
	
	public Boolean checkedStatus(){
		return checked;
	}
	
	public void setChecked(boolean isChecked){
		checked = isChecked;
	}
	
	@Override
	public String toString(){
		return toDoText;
	}
	
}
