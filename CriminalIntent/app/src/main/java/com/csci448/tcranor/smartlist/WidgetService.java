package com.csci448.tcranor.smartlist;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.Toast;

import com.csci448.tcranor.smartlist.Note;
import com.csci448.tcranor.smartlist.NoteBaseHelper;
import com.csci448.tcranor.smartlist.NoteHolder;
import com.csci448.tcranor.smartlist.NoteListFragment;
import com.csci448.tcranor.smartlist.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by EPF on 17/04/2017.
 */

public class WidgetService extends RemoteViewsService {

    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {

        return new ListViewRemoteViewsFactory(this.getApplicationContext(), intent);

    }
}

class ListViewRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private ArrayList<String> records;
    private List<Note>myListTitle;
    private SQLiteDatabase mDatabase;

    public ListViewRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
    }

    // Initialize the data set.
    public void onCreate() {

        // In onCreate() you set up any connections / cursors to your data source. Heavy lifting,
        // for example downloading or creating content etc, should be deferred to onDataSetChanged()
        // or getViewAt(). Taking more than 20 seconds in this call will result in an ANR.

        NoteBaseHelper db = new NoteBaseHelper(this.mContext);
        NoteBaseHelper mDbHelper = new NoteBaseHelper(this.mContext);

        NoteHolder nh = new NoteHolder(this.mContext);

        //get database
        mDatabase = db.getWritableDatabase();


        myListTitle=nh.getNotes();
        records=new ArrayList<String>();

        for (Note e: myListTitle){
            records.add(e.getTitle().toString());
        }

        ArrayList<String>listTitle = new ArrayList<String>() {{
            add("A");
            add("B");
            add("C");
        }};

        //records=new ArrayList<String>();
        Log.d("List",listTitle.get(0));
    }
    // Given the position (index) of a WidgetItem in the array, use the item's text value in
    // combination with the app widget item XML file to construct a RemoteViews object.
    public RemoteViews getViewAt(int position) {

        // position will always range from 0 to getCount() - 1.
        // Construct a RemoteViews item based on the app widget item XML file, and set the
        // text based on the position.
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
        // feed row
        String data=records.get(position);
        String title = myListTitle.get(position).getTitle();
        String details = myListTitle.get(position).getDetails();
        boolean done = myListTitle.get(position).isCompleted();
        int priority = myListTitle.get(position).getPriority();

        Log.d("priority", ""+priority);
        rv.setTextViewText(R.id.title, data);
        rv.setTextViewText(R.id.details, details);

        rv.getLayoutId();

        if(done){
            rv.setImageViewResource(R.id.checked,R.drawable.checked);
        }else{
            rv.setImageViewResource(R.id.checked,R.drawable.uncheck);
        }

        if(priority<4){
            rv.setImageViewResource(R.id.priority,R.drawable.priority_low);
        }else if(priority==5){
            rv.setImageViewResource(R.id.priority,R.drawable.priority_high);
        }

        // end feed row

              // Next, set a fill-intent, which will be used to fill in the pending intent template
        // that is set on the collection view in ListViewWidgetProvider.

        Bundle extras = new Bundle();
        extras.putInt(WidgetProvider.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtra("TODO List",data);
        fillInIntent.putExtras(extras);

        // Make it possible to distinguish the individual on-click
        // action of a given item
        rv.setOnClickFillInIntent(R.id.title, fillInIntent);//call on update of the provider
        rv.setOnClickFillInIntent(R.id.details, fillInIntent);
        rv.setOnClickFillInIntent(R.id.checked, fillInIntent);


        // Return the RemoteViews object.
        return rv;

    }

    public int getCount(){

        Log.e("size=",records.size()+"");
        return records.size();

    }

    public void onDataSetChanged(){
        // Fetching JSON data from server and add them to records arraylist
    }

    public int getViewTypeCount(){
        return 1;
    }

    public long getItemId(int position) {
        return position;
    }

    public void onDestroy(){
        records.clear();
    }

    public boolean hasStableIds() {
        return true;
    }

    public RemoteViews getLoadingView() {
        return null;
    }
}
