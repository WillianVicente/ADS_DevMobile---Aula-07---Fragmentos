package usjt.com.br.paises;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ListarPaisesActivity extends Activity {
    private List<String> lista;
    public static final String DESCRICAO = "br.usjt.paises.descricao";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent origemIntent = getIntent();
        String chave = origemIntent.getStringExtra(MainActivity.NOME);

        if (chave.equals("Todas")) {
            chave = "all";
            new ConsomeWS().execute("https://restcountries.eu/rest/v2/" + chave + "?fields=name");
        } else {
            new ConsomeWS().execute("https://restcountries.eu/rest/v2/region/" + chave + "?fields=name");
        }
    }

    public void createView(){
        setContentView(R.layout.activity_listar_paises);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        ListView listView =
                findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent intent = new Intent(ListarPaisesActivity.this, DetalheChamadoActivity.class);
                intent.putExtra(DESCRICAO, lista.get(pos));
                startActivity(intent);
            }
        });
    }

    private class ConsomeWS extends AsyncTask <String, Void, String>{
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
                List<String> listaNomes = new ArrayList<>();
                int count = 1;
                JSONArray names = new JSONArray(json);


                while(count > 0){
                    try {
                        JSONObject pais =
                                names.getJSONObject(count);

                        String name =
                                pais.getString("name");
                        count += 1;
                        listaNomes.add(name);
                    }catch(Exception ex){
                        count = -1;
                    }
                }
                lista = listaNomes;
                createView();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}






