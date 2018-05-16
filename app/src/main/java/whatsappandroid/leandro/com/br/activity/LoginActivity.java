package whatsappandroid.leandro.com.br.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import whatsappandroid.leandro.com.br.R;

public class LoginActivity extends AppCompatActivity {

    private EditText telefone;
    private EditText nome;
    private EditText codPais;
    private EditText codArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        telefone = findViewById(R.id.edit_telefone);
        nome = findViewById(R.id.edit_nome);
        codPais = findViewById(R.id.edit_cod_pais);
        codArea = findViewById(R.id.edit_cod_area);

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
    }
}
