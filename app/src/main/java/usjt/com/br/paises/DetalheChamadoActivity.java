package usjt.com.br.paises;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Note-Willian on 3/27/2018.
 */

public class DetalheChamadoActivity extends AppCompatActivity {
    private TextView nomeSelecionadoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_chamado);
        nomeSelecionadoTextView =
                (TextView) findViewById(R.id.nomeSelecionadoTextView);
        Intent i = getIntent();
        String selecionado = i.getStringExtra(ListarPaisesActivity.DESCRICAO);
        nomeSelecionadoTextView.setText(selecionado);
    }
}
