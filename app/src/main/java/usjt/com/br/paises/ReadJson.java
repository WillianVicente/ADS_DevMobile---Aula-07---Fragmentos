package usjt.com.br.paises;

import android.util.JsonReader;
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

    public static List<String> getLista() {
        return lista;
    }

    public static void setLista(List<String> lista) {
        ReadJson.lista = lista;
    }

    private static List<String> lista;


    static List<String> SendGet(final String URL) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL restcountries = new URL(URL);
                    HttpsURLConnection myConnection =
                            (HttpsURLConnection) restcountries.openConnection();

                    if (myConnection.getResponseCode() == 200) {
                        InputStream responseBody = myConnection.getInputStream();
                        setLista(readJsonStream(responseBody));
                        myConnection.disconnect();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        Thread.sleep(5000);

        return getLista();
    }

    private static ArrayList<String> readJsonStream(InputStream in) throws IOException {
        try (JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"))) {
            return readMessagesArray(reader);
        }
    }

    private static ArrayList<String> readMessagesArray(JsonReader reader) throws IOException {
        //List<Pais> paises = new ArrayList<Pais>();
        ArrayList<String> lista = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            lista.add(readMessage(reader));
        }
        reader.endArray();
        return lista;
    }

    private static String readMessage(JsonReader reader) throws IOException {

        String nome = "";
       //String codigo3;
       //String capital;
       //String regiao;
       //String subRegiao;
       //String demonimo;
       //int populacao;
       //int area;
       //String bandeira;
       //double gini;
       //ArrayList<String> idiomas;
       //ArrayList<String> moedas;
       //ArrayList<String> dominios;
       //ArrayList<String> fusos;
       //ArrayList<String> fronteiras;
       //double latitude;
       //double longitude;

        reader.beginObject();
        while (reader.hasNext()) {

            String name = reader.nextName();
            if (name.equals("name")) {
                nome = reader.nextString();
            } else {
            reader.skipValue();
        }
        }
        reader.endObject();
        return nome;
    }
}
