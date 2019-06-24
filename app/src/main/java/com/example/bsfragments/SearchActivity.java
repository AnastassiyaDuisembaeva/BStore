package com.example.bsfragments;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

public class SearchActivity extends  ListActivity {
    private EditText text;
    private Button add;
    private RecordsDbHelper mDbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //Создаем экземпляр БД
        mDbHelper = new RecordsDbHelper(this);
        //Открываем БД для записи
        mDbHelper.open();
        //Получаем Intent
        Intent intent = getIntent();
        //Проверяем тип Intent
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            //Берем строку запроса из экстры
            String query = intent.getStringExtra(SearchManager.QUERY);
            //Выполняем поиск
            showResults(query);
        }

        add = (Button) findViewById(R.id.add);
        text = (EditText) findViewById(R.id.text);
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String data = text.getText().toString();
                if (!data.equals("")) {
                    saveTask(data);
                    text.setText("");
                }
            }
        });
    }

    private void saveTask(String data) {
        mDbHelper.createRecord(data);
    }

    private void showResults(String query) {
        //Ищем совпадения
        Cursor cursor = mDbHelper.fetchRecordsByQuery(query);
        startManagingCursor(cursor);
        String[] from = new String[] { RecordsDbHelper.KEY_DATA };
        int[] to = new int[] { R.id.text1 };

        SimpleCursorAdapter records = new SimpleCursorAdapter(this,
                R.layout.record, cursor, from, to);
        //Обновляем адаптер
        setListAdapter(records);
    }
}
