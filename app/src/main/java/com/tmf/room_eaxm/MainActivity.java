package com.tmf.room_eaxm;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private EditText mTodoEditText;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTodoEditText = findViewById(R.id.todo_edit);
        mResultTextView = findViewById(R.id.result_text);

        // https://developer.android.google.cn/topic/libraries/architecture/viewmodel?hl=ko#java
        //MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        /**
         // ViewModel 사용법(업데이트)
         NormalViewModel normalViewModel = new ViewModelProvider(this).get(NormalViewModel.class);
         normalViewModel.print();

         MyAndroidViewModel myAndroidViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MyAndroidViewModel.class);
         myAndroidViewModel.print();
         */

        MainViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(MainViewModel.class);

        // UI 갱신
        viewModel.getAll().observe(this, todos -> {
            mResultTextView.setText(todos.toString());
        });

        // 버튼 클릭 시 DB에 insert
        findViewById(R.id.add_button).setOnClickListener(view -> {
            viewModel.insert(new Todo(mTodoEditText.getText().toString()));
        });
    }


}
