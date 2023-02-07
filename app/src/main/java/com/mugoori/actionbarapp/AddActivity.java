package com.mugoori.actionbarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mugoori.actionbarapp.model.Post;

public class AddActivity extends AppCompatActivity {

    EditText editTitle;
    EditText editBody;
    Button btnSave;

    public static final int SAVE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().setTitle("포스팅 생성");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTitle = findViewById(R.id.editTitle);
        editBody = findViewById(R.id.editBody);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePosting();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 액션바에 메뉴가 나오도록 설정한다
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if ( itemId == R.id.menuSave ) {
            savePosting();
        }

        return super.onOptionsItemSelected(item);
    }

    // 액션바 백버튼 이벤트처리
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    void savePosting(){

        String title = editTitle.getText().toString().trim();
        String body = editBody.getText().toString().trim();

        if (title.isEmpty() || body.isEmpty()){
            Toast.makeText(AddActivity.this, "필수 항목 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        Post post = new Post(title, body);

        // post 를 메인 액티비티에 전달하고
        Intent intent = new Intent();
        intent.putExtra("post",post);
        setResult(SAVE,intent);

        // 이 액티비티는 종료한다
        finish();

    }
}