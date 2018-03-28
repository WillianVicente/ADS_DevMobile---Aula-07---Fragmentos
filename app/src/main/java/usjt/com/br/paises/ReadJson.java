package usjt.com.br.paises;

import android.util.JsonReader;
import android.util.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Note-Willian on 3/20/2018.
 */

public class ReadJson {

    public static List<Pais> getListaPaises() {
        return listaPaises;
    }

    public static void setListaPaises(List<Pais> listaPaises) {
        ReadJson.listaPaises = listaPaises;
    }

    private static List<Pais> listaPaises;
    private int posicao;

    public static List<String> GetPaisesNames(String URL) throws InterruptedException {
        List<String> listaNomes = new ArrayList<>();
        SendGet(URL);

        for (Pais pais: listaPaises) {
            listaNomes.add(pais.getNome());
        }

        return listaNomes;
    }

    public static Pais GetDetalhesDoPais(String nome){
        Pais paisSelecionado = new Pais();

        for (Pais pais: listaPaises){
            if(pais.getNome().equals(nome)){
                paisSelecionado = pais;
            }
        }
        return paisSelecionado;
    }

    public static void SendGet(final String URL) throws InterruptedException {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        URL restcountries = new URL(URL);
                        HttpsURLConnection myConnection =
                                (HttpsURLConnection) restcountries.openConnection();

                        if (myConnection.getResponseCode() == 200) {
                            InputStream responseBody = myConnection.getInputStream();
                            setListaPaises(readJsonStream(responseBody));
                            myConnection.disconnect();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            Thread.sleep(5000);
    }

    private static List<Pais> readJsonStream(InputStream in) throws IOException {
        try (JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"))) {
            return readMessagesArray(reader);
        }
    }

    private static List<Pais> readMessagesArray(JsonReader reader) throws IOException {
        List<Pais> lista = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            lista.add(readMessage(reader));
        }
        reader.endArray();
        return lista;
    }

    private static Pais readMessage(JsonReader reader) throws IOException {
        Pais pais = new Pais();
        List<Double> latlng;

        reader.beginObject();
        while (reader.hasNext()) {

            String name = reader.nextName();
            if (name.equals("name")) {
                try{
                    pais.setNome(reader.nextString());
                }catch(Exception e){
                    reader.nextNull();
                    pais.setNome("");
                }
            }else if (name.equals("alpha3Code")) {
                try{
                    pais.setCodigo3(reader.nextString());
                }catch(Exception e){
                    reader.nextNull();
                    pais.setCodigo3("");
                }
            }else if (name.equals("capital")) {
                try{
                    pais.setCapital(reader.nextString());
                }catch(Exception e){
                    reader.nextNull();
                    pais.setCapital("");
                }
            }else if (name.equals("region")) {
                try{
                    pais.setRegiao(reader.nextString());
                }catch(Exception e){
                    reader.nextNull();
                    pais.setRegiao("");
                }
            }else if (name.equals("subregion")) {
                try{
                    pais.setSubRegiao(reader.nextString());
                }catch(Exception e){
                    reader.nextNull();
                    pais.setSubRegiao("");
                }
            }else if (name.equals("demonym")) {
                try{
                    pais.setDemonimo(reader.nextString());
                }catch(Exception e){
                    reader.nextNull();
                    pais.setDemonimo("");
                }
            }else if (name.equals("population")) {
                try{
                    pais.setPopulacao(reader.nextInt());
                }catch(Exception e){
                    reader.nextNull();
                    pais.setPopulacao(0);
                }
            }else if (name.equals("area")) {
                  try{
                      pais.setArea(reader.nextString());
                  }catch(Exception e){
                      reader.nextNull();
                      pais.setArea("");
                  }
            }else if (name.equals("flag")) {
                try{
                    pais.setBandeira(reader.nextString());
                }catch(Exception e){
                    reader.nextNull();
                    pais.setBandeira("");
                }
            }else if (name.equals("gini")) {
                try{
                    pais.setGini(reader.nextDouble());
                }catch(Exception e){
                    reader.nextNull();
                    pais.setGini(0.0);
                }
            //}else if (name.equals("latlng") && reader.peek() != JsonToken.NULL) {
            //    latlng = readDoublesArray(reader);
            //    pais.setLatitude(latlng.get(0));
            //    pais.setLongitude(latlng.get(1));
            }
            else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return pais;
    }
    public static List<Double> readDoublesArray(JsonReader reader) throws IOException {
        List<Double> doubles = new ArrayList<Double>();

        reader.beginArray();
        while (reader.hasNext()) {
            doubles.add(reader.nextDouble());
        }
        reader.endArray();
        return doubles;
    }
}
