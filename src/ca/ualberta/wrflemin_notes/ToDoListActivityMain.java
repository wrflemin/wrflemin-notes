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


package ca.ualberta.wrflemin_notes;

import com.example.wrflemin_notes.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;

public class ToDoListActivityMain extends Activity {
	//initializes the empty todo list
	//TODO initialize the old values from storage
	private ToDoList activeToDoList;
	private CheckedTextView checkedTextView;
	private InsertToDoAdapter checkboxAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_to_do_list_home);
		
		activeToDoList = new ToDoList();
		checkboxAdapter = new InsertToDoAdapter(this,
		        R.layout.todo_with_checkbox, activeToDoList.getToDoList());
		
		
		ListView listView = (ListView) findViewById(R.id.toDoListView);
		listView.setAdapter(checkboxAdapter);
		
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
		if (!toDoText.isEmpty()){
			todo_textbox.getText().clear();
			newToDo = new ToDoItem(toDoText, false);
			activeToDoList.add(newToDo);
			checkboxAdapter.notifyDataSetChanged();
		}	
		
	}
	
	
	public void checkBox(View view){
		checkedTextView = (CheckedTextView) view;
		int position = (Integer)checkedTextView.getTag();
		
		if (checkedTextView.isChecked()){
			checkedTextView.setChecked(false);
			activeToDoList.getToDo(position).setChecked(false);
		}
		else{
			checkedTextView.setChecked(true);
			activeToDoList.getToDo(position).setChecked(true);
		}
	}
	
	
}
