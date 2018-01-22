package grupo_go_ra_ri.dam.isi.frsf.codeforces;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

import grupo_go_ra_ri.dam.isi.frsf.codeforces.adaptadores.adaptador_competencia;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.model.Contest;

public class contest_list extends Fragment {

    private ListView lvCompetencias;
    private Contest constest = new Contest();
    private ArrayList<Contest> lista_competencias;
    private adaptador_competencia adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contest, container, false);

        lvCompetencias = (ListView)view.findViewById(R.id.lv_contest);

        lista_competencias = GetArrayItems();

        adapter = new adaptador_competencia(getActivity(),lista_competencias);
        lvCompetencias.setAdapter(adapter);

        lvCompetencias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();


            }
        });

        return view;
    }

    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
    }

    private ArrayList<Contest> GetArrayItems(){
        ArrayList<Contest> lista_competencias = new ArrayList<>();
        Contest comp1 = new Contest();
        Contest comp2 = new Contest();
        Contest comp3 = new Contest();
        Contest comp4 = new Contest();

        comp1.setName("Competencia1");
        comp2.setName("Competencia2");
        comp3.setName("Competencia3");
        comp4.setName("Competencia4");

        lista_competencias.add(comp1);
        lista_competencias.add(comp2);
        lista_competencias.add(comp3);
        lista_competencias.add(comp4);

        return lista_competencias;
    }
}
