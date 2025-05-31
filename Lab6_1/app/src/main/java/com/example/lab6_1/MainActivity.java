package com.example.lab6_1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;

import com.example.lab6_1.dialog.CommentDialog;
import com.example.lab6_1.dialog.DemoDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Click handler for buttons
    public void goodBye(View view) {
        int id = view.getId();
        if (id == R.id.btnComment) {
            DialogFragment commentDialog = CommentDialog.newInstance();
            commentDialog.show(getSupportFragmentManager(), "commentDialog");
        } else if (id == R.id.btnGoodbye) {
            Toast.makeText(this, "Goodbye", Toast.LENGTH_LONG).show();
        } else if (id == R.id.btnDone) {
            DialogFragment demoDialog = DemoDialog.newInstance();
            demoDialog.show(getSupportFragmentManager(), "demoDialog");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mExit)
            finishAffinity();
        return super.onOptionsItemSelected(item);
    }
}