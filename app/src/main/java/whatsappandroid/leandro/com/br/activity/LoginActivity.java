package whatsappandroid.leandro.com.br.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.HashMap;
import java.util.Random;

import whatsappandroid.leandro.com.br.R;
import whatsappandroid.leandro.com.br.helper.Preferencias;

public class LoginActivity extends AppCompatActivity {

    private EditText telefone;
    private EditText nome;
    private EditText codPais;
    private EditText codArea;
    private Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        telefone = findViewById(R.id.edit_telefone);
        nome = findViewById(R.id.edit_nome);
        codPais = findViewById(R.id.edit_cod_pais);
        codArea = findViewById(R.id.edit_cod_area);
        cadastrar = findViewById(R.id.bt_cadastrar);

        /* Definir as Máscaras */
        SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter(" NNNNN-NNNN ");
        MaskTextWatcher maskTelefone = new MaskTextWatcher(telefone, simpleMaskTelefone);

        telefone.addTextChangedListener(maskTelefone);

        SimpleMaskFormatter simpleMaskCodPais = new SimpleMaskFormatter(" +NN ");
        MaskTextWatcher maskCodPais = new MaskTextWatcher(codPais, simpleMaskCodPais);
        codPais.addTextChangedListener(maskCodPais);

        SimpleMaskFormatter simpleMaskCodArea = new SimpleMaskFormatter("NN");
        MaskTextWatcher maskCodArea = new MaskTextWatcher(codArea, simpleMaskCodArea);
        codArea.addTextChangedListener(maskCodArea);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeUsuario = nome.getText().toString();
                String telefoneCompleto = codPais.getText().toString() + codArea.getText().toString()
                        + telefone.getText().toString();
                String telefoneSemFormatacao = telefoneCompleto.replace("+", "");
                telefoneSemFormatacao = telefoneSemFormatacao.replace("-", "");
                telefoneSemFormatacao = telefoneSemFormatacao.replace(" ", "");

                //Log.i("TELEFONE","T: " + telefoneSemFormatacao);
                // Gerar Token
                Random randomico = new Random();
                int numeroRandomico = randomico.nextInt(8999) - 1000;

                String token = String.valueOf(numeroRandomico);

                //Log.i("TOKEN","T: " + token);

                Preferencias preferencias = new Preferencias(LoginActivity.this);
                preferencias.salvarUsuarioPreferencias(nomeUsuario, telefoneSemFormatacao, token);

                HashMap<String, String> usuario = preferencias.getDadosUsuario();

                Log.i("TOKEN", "NOME:" + usuario.get("nome") + "; FONE: " +
                        usuario.get("telefone") + "; TOKEN: " + usuario.get("token"));

            }
        });
    }
}
