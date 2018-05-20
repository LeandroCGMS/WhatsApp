package whatsappandroid.leandro.com.br.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Random;

import whatsappandroid.leandro.com.br.R;
import whatsappandroid.leandro.com.br.config.ConfiguracaoFirebase;
import whatsappandroid.leandro.com.br.helper.Permissao;
import whatsappandroid.leandro.com.br.helper.Preferencias;

public class LoginActivity extends AppCompatActivity {

    private DatabaseReference referenciaDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        referenciaDatabase = ConfiguracaoFirebase.getFirebase();
        referenciaDatabase.child("pontos").setValue("800");

    }

    public void abrirCadastroUsuario(View view) {

        Intent intent = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
        startActivity(intent);
    }
}
