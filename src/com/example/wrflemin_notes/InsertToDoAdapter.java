package com.example.wrflemin_notes;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.CheckedTextView;
import android.widget.Toast;

public class InsertToDoAdapter extends ArrayAdapter<ToDoItem> {
	
	private Context context;
	private int resource;
	private ArrayList<ToDoItem> objects;

	public <T> InsertToDoAdapter(Context context, int resource, ArrayList<ToDoItem> objects) {
		super(context, resource, objects);
		this.context = context;
		this.resource = resource;
		this.objects = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.todo_with_checkbox, parent, false);
		}
			CheckedTextView checkedTextBox = (CheckedTextView) 
					convertView.findViewById(R.id.list_item_with_checkbox);
			checkedTextBox.setText(objects.get(position).toString());
			/*checkedTextBox.setOnLongClickListener(new OnLongClickListener() { 
		        @Override
		        public boolean onLongClick(View v) {
		        	//use floating menu to show options to user
		            return true;
		        }
			});*/
		return convertView;
		
		
	}
	

}
