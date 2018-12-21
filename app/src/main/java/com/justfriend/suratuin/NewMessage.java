package com.justfriend.suratuin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class NewMessage extends AppCompatActivity {
    private static final int PICKFILE_RESULT_CODE = 1;
    TextView tv_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tv_path = findViewById(R.id.textView2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_message, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_savedraft:
                Toast.makeText(this, "Berhasil Menyimpan", Toast.LENGTH_SHORT)
                        .show();
                finish();
                return true;
            case R.id.action_batal:
                finish();
                return true;
            case R.id.home:
                onBackPressed();
                this.finish();
                return true;
            case R.id.action_attach:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, PICKFILE_RESULT_CODE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PICKFILE_RESULT_CODE:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this, "File Terpilih", Toast.LENGTH_SHORT)
                            .show();
                    String filepath = data.getData().getPath();
                    tv_path.setText(filepath);
                }
                break;
        }
    }
}
