package ca.ualberta.wrflemin_notes;

import java.util.ArrayList;

import ca.ualberta.wrflemin_notes.utils.ContextMenuManager;

import com.example.wrflemin_notes.R;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

public class InsertToDoAdapter extends ArrayAdapter<ToDoItem> {
	
	private Context context;
	private int resource;
	private ArrayList<ToDoItem> objects;
	private ContextMenuManager contextMenuManager;
	
	public <T> InsertToDoAdapter(Context context, int resource, ArrayList<ToDoItem> objects) {
		super(context, resource, objects);
		this.context = context;
		this.resource = resource;
		this.objects = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		//final Activity activity = (Activity) context;
		
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(resource, parent, false);
		}
			CheckedTextView checkedTextBox = (CheckedTextView) 
						convertView.findViewById(R.id.list_item_with_checkbox);
			checkedTextBox.setText(objects.get(position).toString());
			checkedTextBox.setChecked(objects.get(position).checkedStatus());
			checkedTextBox.setTag(position);
			
			//sets up the context menu for each checkedTextView
			contextMenuManager = new ContextMenuManager();
			checkedTextBox.setOnLongClickListener(new View.OnLongClickListener() {
			    // Called when the user long-clicks on someView
			    public boolean onLongClick(View view) {
			    	
			        if (contextMenuManager.getActionMode() != null) {
			            return false;
			        }

			        // Start the CAB using the ActionMode.Callback defined above
			        contextMenuManager.setActionMode(((Activity) context).startActionMode(contextMenuManager));
			        view.setSelected(true);
			        return true;
			    }
			});
		return convertView;
		
		
	}

}
