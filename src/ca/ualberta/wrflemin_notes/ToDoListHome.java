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

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/*Toast code
 *Context context = getApplicationContext();
 *CharSequence text = "Hello toast!";
 *int duration = Toast.LENGTH_SHORT;
 *Toast toast = Toast.makeText(context, text, duration);
 *toast.show();*/


public class ToDoListHome extends ActionBarActivity {
	//initializes the empty todo list
	//TODO initialize the old values from storage
	protected ToDoList activeToDoList;
	protected CheckedTextView checkedTextView;
	protected InsertToDoAdapter checkboxAdapter;
	protected Activity activity = this;
	protected ActionMode mActionMode;
	
	
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
		
		
		final ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

		    // Called when the action mode is created; startActionMode() was called
		    @Override
		    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
		        // Inflate a menu resource providing context menu items
		        MenuInflater inflater = mode.getMenuInflater();
		        inflater.inflate(R.menu.context_menu, menu);
		        return true;
		    }

		    // Called each time the action mode is shown. Always called after onCreateActionMode, but
		    // may be called multiple times if the mode is invalidated.
		    @Override
		    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
		        return false; // Return false if nothing is done
		    }

		    // Called when the user selects a contextual menu item
		    @Override
		    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
		        switch (item.getItemId()) {
		            case R.id.delete:
		                //shareCurrentItem();
		                mode.finish(); // Action picked, so close the CAB
		                return true;
		            default:
		                return false;
		        }
		    }

		    // Called when the user exits the action mode
		    @Override
		    public void onDestroyActionMode(ActionMode mode) {
		        mActionMode = null;
		    }
		};

		listView.setOnLongClickListener(new View.OnLongClickListener() {
		    // Called when the user long-clicks on someView
		    public boolean onLongClick(View view) {
		        if (mActionMode != null) {
		            return false;
		        }

		        // Start the CAB using the ActionMode.Callback defined above
		        mActionMode = activity.startActionMode(mActionModeCallback);
		        view.setSelected(true);
		        return true;
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
		if (!toDoText.isEmpty()){
			todo_textbox.getText().clear();
			newToDo = new ToDoItem(toDoText, false);
			activeToDoList.add(newToDo);
			checkboxAdapter.notifyDataSetChanged();
		}		
		
	}
	
	public void getFocus(View view){
		view.requestFocus();
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
