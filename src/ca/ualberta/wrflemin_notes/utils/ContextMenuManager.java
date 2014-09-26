//code modified from API Guides|Menus at http://developer.android.com/guide/topics/ui/menus.html.

package ca.ualberta.wrflemin_notes.utils;

import com.example.wrflemin_notes.R;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class ContextMenuManager implements ActionMode.Callback{
	
	private ActionMode mActionMode;
	
	public ContextMenuManager() {
	}
	
	public ActionMode getActionMode(){
		return mActionMode;
	}
	public void setActionMode(ActionMode actionMode){
		mActionMode = actionMode;
	}
	
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
                deleteCurrentItem();
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

    public void deleteCurrentItem(){

    }
}
