package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.codepath.simpletodo.adaptor.ItemAdapter;
import com.codepath.simpletodo.model.Item;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Item> items;
    ItemAdapter itemsAdapter;
    ListView lvItems;
    ExpandableListView elvUrgencies;
    ExpandableListAdapter urgencyAdaptor;
    private static final int REQUEST_ID_EDIT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Simple Todo");
        setSupportActionBar(toolbar);

        lvItems = (ListView)findViewById(R.id.lvItems);
        readItems();
        itemsAdapter = new ItemAdapter(this, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        Item newItem = new Item(itemText);
        newItem.save();
        itemsAdapter.add(newItem);
        etNewItem.setText("");
    }

    public void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        Item itemRemoved = items.get(position);
                        itemRemoved.delete();
                        items.remove(position);
                        itemsAdapter.notifyDataSetChanged();
                        return true;
                    }
                }
        );
        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent editIntend = new Intent(MainActivity.this, EditItemActivity.class);
                        editIntend.putExtra("pos", position);
                        editIntend.putExtra("desc", items.get(position).description);
                        startActivityForResult(editIntend, REQUEST_ID_EDIT);
                    }
                }
        );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == REQUEST_ID_EDIT) {
            int position = data.getExtras().getInt("pos", -1);
            String desc = data.getExtras().getString("desc");

            if(position >= 0 && position < items.size()) {
                Item item = items.get(position);
                item.description = desc;
                item.save();
                itemsAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void readItems() {
        items = Item.getAll();
    }
}
