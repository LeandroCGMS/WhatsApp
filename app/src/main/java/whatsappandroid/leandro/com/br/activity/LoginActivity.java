package whatsappandroid.leandro.com.br.activity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
//import android.support.v7.app.AlertDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.*;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Random;

import whatsappandroid.leandro.com.br.R;
import whatsappandroid.leandro.com.br.config.ConfiguracaoFirebase;
import whatsappandroid.leandro.com.br.helper.Permissao;
import whatsappandroid.leandro.com.br.helper.Preferencias;
import whatsappandroid.leandro.com.br.model.Usuario;

public class LoginActivity extends Activity {

    private DatabaseReference referenciaDatabase;
    private EditText email;
    private EditText senha;
    private Button botaoLogar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        verificarUsuarioLogado();

        email = findViewById(R.id.edit_login_email);
        senha = findViewById(R.id.edit_login_senha);
        botaoLogar = findViewById(R.id.bt_logar);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usuario = new Usuario();
                usuario.setEmail( email.getText().toString() );
                usuario.setSenha( senha.getText().toString() );
                validarLogin();

            }
        });


    }

    private void verificarUsuarioLogado() {
        autenticacao = ConfiguracaoFirebase.getAutenticacao();
        if(autenticacao.getCurrentUser() != null) {
            abrirTelaPrincipal();
        }
    }

    private void validarLogin() {

        autenticacao = ConfiguracaoFirebase.getAutenticacao();
        autenticacao.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if( task.isSuccessful() ) {
                            abrirTelaPrincipal();
                            Toast.makeText(LoginActivity.this, "Sucesso ao fazer " +
                                    "login",Toast.LENGTH_LONG).show();
                        } else {

                            Toast.makeText(LoginActivity.this, "Erro ao fazer " +
                                    "login. Verifique e-mail e senha.",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    private void abrirTelaPrincipal() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    public void abrirCadastroUsuario(View view) {

        Intent intent = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
        startActivity(intent);
    }

}
