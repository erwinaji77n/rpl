package com.justfriend.suratuin;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class NewMessage extends AppCompatActivity {
    private static final int PICKFILE_RESULT_CODE = 1;
    TextView tv_path;
    Button btn_send;
    EditText pengirim, perihal, isi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        pengirim=findViewById(R.id.txtPenerima);
        perihal=findViewById(R.id.txtPerihal);
        isi=findViewById(R.id.txtIsi);
        final ProgressDialog loading = new ProgressDialog(NewMessage.this);
        tv_path = findViewById(R.id.textView2);
        btn_send=findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=pengirim.getText().toString();
                String b=perihal.getText().toString();
                String c=isi.getText().toString();
                if (a.matches("")) {
                    pengirim.setError("Penerima Tidak Boleh Kosong");
                    return;
                }
                if (b.matches("")) {
                    pengirim.setError("Perihal Tidak Boleh Kosong");
                    return;
                }
                if (c.matches("")) {
                    pengirim.setError("Isi Tidak Boleh Kosong");
                    return;
                }
                loading.setTitle("Kirim Pesan");
                loading.setMessage("Sedang Mengirim");
                loading.setCancelable(false);
                loading.setIndeterminate(true);
                loading.show();
                final Handler h = new Handler() {
                    @Override
                    public void handleMessage(Message message) {
                        loading.dismiss();
                    }
                };
                h.sendMessageDelayed(new Message(), 10000);
                Toast.makeText(NewMessage.this, "Berhasil Mengirim", Toast.LENGTH_SHORT).show();
                finish();

            }
        });
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
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(false);
                builder.setTitle("Batal Mengirim Pesan");
                builder.setMessage("Apakah Anda Yakin?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user pressed "yes", then he is allowed to exit from application
                        finish();
                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user select "No", just cancel this dialog and continue with app
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
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
