package usjt.com.br.paises;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
        setContentView(R.layout.activity_detalhe_pais);
        Intent intent = getIntent();
        String paisSelecionado = intent.getStringExtra(ListarPaisesActivity.DESCRICAO);

        new ConsomeWS().execute("https://restcountries.eu/rest/v2/name/" + paisSelecionado);
    }

    private void CreateView(Pais pais){

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

    private class ConsomeWS extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(strings[0])
                    .build();
            try{
                Response response = client.newCall(request).execute();
                return response.body().string();
            }
            catch(IOException e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String json) {
            try {
                Pais pais = new Pais();

                JSONArray paisInfo = new JSONArray(json);

                JSONObject paisDetails = paisInfo.getJSONObject(0);
                pais.setNome(paisDetails.getString("name"));
                pais.setCodigo3(paisDetails.getString("alpha3Code"));
                pais.setCapital(paisDetails.getString("capital"));
                pais.setRegiao(paisDetails.getString("region"));
                pais.setSubRegiao(paisDetails.getString("subregion"));
                pais.setDemonimo(paisDetails.getString("demonym"));
                pais.setPopulacao(paisDetails.getInt("population"));
                pais.setArea(paisDetails.getString("area"));
                pais.setBandeira(paisDetails.getString("flag"));
                pais.setGini(paisDetails.getDouble("gini")); //TODO: Gini null
                //pais.setIdiomas(paisDetails.getString("languages"));
                //pais.setMoedas(paisDetails.getString("currencies"));
                //pais.setDominios(paisDetails.getString("translations"));
                //JSONArray languages = paisDetails.getJSONArray("timezones");

                //int teste = languages.length();
                //int teste2;
                //JSONObject language =
                //        languages.getJSONObject(0);

                //pais.setFusos(language.getString(""));
                //pais.setFronteiras(paisDetails.getString("borders"));
                //pais.setLatitude(paisDetails.getDouble("latlng"));
                //pais.setLongitude(paisDetails.getDouble("latlng"));

                CreateView(pais);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
