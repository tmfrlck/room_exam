package com.tmf.room_eaxm;

import android.os.Bundle;
import android.view.View;
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

        // [에러] https://www.google.com/search?q=Caused+by%3A+android.view.InflateException%3A+Binary+XML+file+line+%232%3A+Error+inflating+class+androidx.constraintlayout.ConstraintLayout&rlz=1C1CHBD_koKR941KR941&oq=Caused+by%3A+android.view.InflateException%3A+Binary+XML+file+line+%232%3A+Error+inflating+class+androidx.constraintlayout.ConstraintLayout&aqs=chrome..69i57.230j0j1&sourceid=chrome&ie=UTF-8
        // [해결] https://stackoverflow.com/questions/57324498/android-view-inflateexception-binary-xml-file-line-2-error-inflating-class-an

        mTodoEditText = findViewById(R.id.todo_edit);
        mResultTextView = findViewById(R.id.result_text);

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "todo-db")
                .allowMainThreadQueries()// 지금은 Main Thread에서 동작하도록 허용하지만, 실무에선 background thread에서 동작하도록 작성하도록.
                .build();// db는 무조건 background에서 동작하지 않으면 에러 발생.

        mResultTextView.setText(db.todoDao().getAll().toString());

        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.todoDao().insert(new Todo(mTodoEditText.getText().toString()));
                mResultTextView.setText(db.todoDao().getAll().toString());
            }
        });

    }
}
