package com.tmf.room_eaxm;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {

    private EditText mTodoEditText;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTodoEditText = findViewById(R.id.todo_edit);
        mResultTextView = findViewById(R.id.result_text);

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "todo-db")
                .allowMainThreadQueries()// 지금은 Main Thread에서 동작하도록 허용하지만, 실무에선 background thread에서 동작하도록 작성하도록.
                .build();// db는 무조건 background에서 동작하지 않으면 에러 발생.

        // UI 갱신
//        db.todoDao().getAll().observe(this, new Observer<List<Todo>>() {
//            @Override
//            public void onChanged(List<Todo> todos) {
//            }
//        });
        db.todoDao().getAll().observe(this, todos -> {
            mResultTextView.setText(todos.toString());
        });

        // 버튼 클릭 시 DB에 insert
        findViewById(R.id.add_button).setOnClickListener(view -> {
            db.todoDao().insert(new Todo(mTodoEditText.getText().toString()));
        });

    }
}
