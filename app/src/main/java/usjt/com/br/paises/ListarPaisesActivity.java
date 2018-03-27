package usjt.com.br.paises;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.IOException;
import java.util.List;


public class ListarPaisesActivity extends Activity {
    private List<String> lista;
    public static final String DESCRICAO =
            "br.usjt.paises.descricao";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listar_paises);
        Intent origemIntent = getIntent();
        String chave = origemIntent.getStringExtra (MainActivity.NOME);


        try {
            lista = buscaPaises(chave);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        ArrayAdapter <String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        ListView listView =
                findViewById(R.id.listView);
        listView.setAdapter(adapter);

         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                 Intent intent = new Intent (ListarPaisesActivity.this, DetalheChamadoActivity.class);
                 intent.putExtra(DESCRICAO, lista.get(pos));
                 startActivity(intent);
             }
         });
    }

    public List<String> buscaPaises(String chave) throws IOException, InterruptedException {

        if(chave.equals("Todas")){
            chave = "all";
            return ReadJson.SendGet("https://restcountries.eu/rest/v2/" + chave);
        }else{
            return ReadJson.SendGet("https://restcountries.eu/rest/v2/region/" + chave);
        }

    }
}


