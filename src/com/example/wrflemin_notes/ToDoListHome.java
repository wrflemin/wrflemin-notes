/*
    Copyright 2014 Wyatt Fleming

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */


package com.example.wrflemin_notes;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ToDoListHome extends ActionBarActivity {
	//initializes the empty todo list
	//TODO initialize the old values from storage
	protected ToDoList activeToDoList;
	protected CheckedTextView checkedTextView;
	protected InsertToDoAdapter checkboxAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_to_do_list_home);
		//TODO initialize the old values from storage
		activeToDoList = new ToDoList();
		checkboxAdapter = new InsertToDoAdapter(this,
		        R.id.toDoListView, activeToDoList.getToDoList() );
		ListView listView = (ListView) findViewById(R.id.toDoListView);
		listView.setAdapter(checkboxAdapter);
		
		//Taken from http://developer.android.com/guide/topics/ui/menus.html#CAB
		
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		listView.setMultiChoiceModeListener(new MultiChoiceModeListener() {

		    @Override
		    public void onItemCheckedStateChanged(ActionMode mode, int position,
		                                          long id, boolean checked) {
		        // Here you can do something when items are selected/de-selected,
		        // such as update the title in the CAB
		    }

		    @Override
		    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
		         //Respond to clicks on the actions in the CAB
		        switch (item.getItemId()) {
		            case R.id.menu_delete:
		                deleteSelectedItems();
		                mode.finish(); // Action picked, so close the CAB
		                return true;
		            default:
		                return false;
		        }
		    	return false;
		    }

		    @Override
		    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
		        // Inflate the menu for the CAB
		        MenuInflater inflater = mode.getMenuInflater();
		        //you need to make a new menu in xml
		        inflater.inflate(R.menu.context, menu);
		        return true;
		    }

		    @Override
		    public void onDestroyActionMode(ActionMode mode) {
		        // Here you can make any necessary updates to the activity when
		        // the CAB is removed. By default, selected items are deselected/unchecked.
		    }

		    @Override
		    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
		        // Here you can perform updates to the CAB due to
		        // an invalidate() request
		        return false;
		    }
		});
		//end borrowed code
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.to_do_list_home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	//called when user clicks Add button
	public void createToDo(View view){
		ToDoItem newToDo;
		EditText todo_textbox = (EditText) findViewById(R.id.todo_textbox);
		String toDoText = todo_textbox.getText().toString();
		if (toDoText != ""){
			todo_textbox.getText().clear();
			newToDo = new ToDoItem(toDoText, false);
			activeToDoList.add(newToDo);
			checkboxAdapter.notifyDataSetChanged();
		}		
		
	}
	
	public void checkBox(View view){
		checkedTextView = (CheckedTextView) view;
		
		if (checkedTextView.isChecked()){
			checkedTextView.setChecked(false);
			activeToDoList.decreaseCheckedCount();
		}
		else{
			checkedTextView.setChecked(true);
			activeToDoList.increaseCheckedCount();
		}
	}
}
