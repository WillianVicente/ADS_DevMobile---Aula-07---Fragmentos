package usjt.com.br.paises;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Willian Vicente Prado
 * RA: 816119009
 * ADSMCA3
 */

public class DetalhePaisFragment extends Fragment {


    public DetalhePaisFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_detalhe_pais, container, false);
    }

}
