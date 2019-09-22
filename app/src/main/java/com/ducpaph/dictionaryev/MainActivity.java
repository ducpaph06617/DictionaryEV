package com.ducpaph.dictionaryev;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtText;
    private RecyclerView lvList;


    private DataBaseHelper dataBaseHelper;

    private WordAdapter wordAdapter;

    private LinearLayoutManager linearLayoutManager;


    private List<Word> wordList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtText = findViewById(R.id.edtText);
        lvList = findViewById(R.id.lvList);

        dataBaseHelper = new DataBaseHelper(this);
        dataBaseHelper.createDataBase();


        wordAdapter = new WordAdapter(wordList, this);
        linearLayoutManager = new LinearLayoutManager(this);

        lvList.setAdapter(wordAdapter);

        lvList.setLayoutManager(linearLayoutManager);



    }

    public void search(View view) {

        String word = edtText.getText().toString().trim();
        wordList.clear();
        // kiểm tra nếu người dùng chưa nhập gì thì dừng lại và thông báo lỗi
        if (word.isEmpty()) {
            edtText.setError("Vui lòng nhập dữ liệu !!!");
            return;
            // nếu chữ ko empty thì tiếp tục tìm kiếm và hiển thị danh sách kết quả lên list nếu có
        } else {
            List<Word> wordList = dataBaseHelper.searchWord(word);
            if (wordList.size()==0){
                Toast.makeText(this, "Không tìm được!!!", Toast.LENGTH_SHORT).show();
            }else {
                //List<Word> wordList = dataBaseHelper.searchWord(word);

                this.wordList.addAll(wordList);
                wordAdapter.notifyDataSetChanged();

            }
        }


    }

}
