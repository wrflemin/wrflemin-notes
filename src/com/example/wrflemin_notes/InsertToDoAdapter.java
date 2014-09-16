package com.example.wrflemin_notes;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.CheckedTextView;

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
	
/*	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		TextView list_item_with_checkbox = (TextView) parent.findViewById(R.id.list_item_with_checkbox);
		
		
	}*/
	

}
