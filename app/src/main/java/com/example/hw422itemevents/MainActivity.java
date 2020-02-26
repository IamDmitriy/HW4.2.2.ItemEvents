package com.example.hw422itemevents;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Resources res;
    private ItemsDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {
        Toolbar myToolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(myToolbar);
        res = getResources();

        ListView listView = findViewById(R.id.listView);
        adapter = new ItemsDataAdapter(this, generatedListContent());
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                showItemData(i);
                return false;
            }
        });

    }

    private List<ItemData> generatedListContent() {
        List<ItemData> listContent = new ArrayList<>();

        listContent.add(new ItemData(getDrawable(R.drawable.health_app),
                "Домашнее задание №1.3.1",
                "Мониторинг здоровья",
                true));

        listContent.add(new ItemData(getDrawable(R.drawable.adaptive_layout_app),
                "Домашнее задание №3.3.1",
                "Адаптивная вёрстка",
                true));

        listContent.add(new ItemData(getDrawable(R.drawable.switch_language_app),
                "Домашнее задание №3.3.2",
                "Переключение языков",
                true));

        return listContent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_open_notes) {
            showToast(res.getString(R.string.open_notes));
            Intent intent = new Intent(MainActivity.this, NotesActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showItemData(int position) {
        ItemData itemData = adapter.getItem(position);
        Toast.makeText(MainActivity.this,
                "Title: " + itemData.getTitle() + "\n" +
                        "Subtitle: " + itemData.getSubtitle() + "\n",
                Toast.LENGTH_SHORT).show();
    }
}
