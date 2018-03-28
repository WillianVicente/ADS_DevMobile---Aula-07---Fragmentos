package usjt.com.br.paises;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Note-Willian on 3/27/2018.
 */

public class DetalheChamadoActivity extends AppCompatActivity {
    private TextView nomeTextView;
    private TextView codigo3TextView;
    private TextView capitalTextView;
    private TextView regiaoTextView;
    private TextView subRegiaoTextView;
    private TextView demonimoTextView;
    private TextView populacaoTextView;
    private TextView areaTextView;
    private TextView bandeiraTextView;
    private TextView giniTextView;
    private TextView idiomasTextView;
    private TextView moedasTextView;
    private TextView dominiosTextView;
    private TextView fusosTextView;
    private TextView fronteirasTextView;
    private TextView latitudeTextView;
    private TextView longitudeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_chamado);

        nomeTextView = (TextView) findViewById(R.id.nomeTextView);
        codigo3TextView = (TextView) findViewById(R.id.codigo3TextView);
        capitalTextView = (TextView) findViewById(R.id.capitalTextView);
        regiaoTextView = (TextView) findViewById(R.id.regiaoTextView);
        subRegiaoTextView = (TextView) findViewById(R.id.subRegiaoTextView);
        demonimoTextView = (TextView) findViewById(R.id.demonimoTextView);
        populacaoTextView = (TextView) findViewById(R.id.populacaoTextView);
        areaTextView = (TextView) findViewById(R.id.areaTextView);
        bandeiraTextView = (TextView) findViewById(R.id.bandeiraTextView);
        giniTextView = (TextView) findViewById(R.id.giniTextView);


        Intent i = getIntent();
        String selecionado = i.getStringExtra(ListarPaisesActivity.DESCRICAO);
        Pais pais = ReadJson.GetDetalhesDoPais(selecionado);

        nomeTextView.setText(pais.getNome());
        codigo3TextView.setText(pais.getCodigo3());
        capitalTextView.setText(pais.getCapital());
        regiaoTextView.setText(pais.getRegiao());
        subRegiaoTextView.setText(pais.getSubRegiao());
        demonimoTextView.setText(pais.getDemonimo());
        populacaoTextView.setText(String.valueOf(pais.getPopulacao()));
        areaTextView.setText(pais.getArea());
        bandeiraTextView.setText(pais.getBandeira());
        giniTextView.setText(String.valueOf(pais.getGini()));
        //idiomasTextView.setText(pais.getIdiomas());
        //moedasTextView.setText(pais.getMoedas());
        //dominiosTextView.setText(pais.getDominios());
        //fusosTextView.setText(pais.getFusos());
        //fronteirasTextView.setText(pais.getFronteiras());
        //latitudeTextView.setText(String.valueOf(pais.getLatitude()));
        //longitudeTextView.setText(String.valueOf(pais.getLongitude()));
    }
}
