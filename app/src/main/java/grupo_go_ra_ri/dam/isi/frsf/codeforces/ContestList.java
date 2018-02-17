package grupo_go_ra_ri.dam.isi.frsf.codeforces;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import grupo_go_ra_ri.dam.isi.frsf.codeforces.adaptadores.adaptador_competencia;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.dao.CodeForcesDaoHTTP;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.dao.MyGenericHTTPClient;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.model.Contest;


public class ContestList extends Fragment {

    private ListView lvCompetencias;
    private ArrayList<Contest> lista_competencias = new ArrayList<>();
    private adaptador_competencia adapter;
    private Handler myHandler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contest, container, false);

        lvCompetencias = (ListView)view.findViewById(R.id.lv_contest);

        //Se carga la lista con los las competencias
        final MyGenericHTTPClient cliente = new MyGenericHTTPClient();
        final String urlString = "http://codeforces.com/api/contest.list?gym=false";
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    String stringOfContests = cliente.performGetCall(urlString, null);
                    System.out.println(stringOfContests);
                    JSONObject obj = new JSONObject(stringOfContests);
                    JSONArray contests = obj.getJSONArray("result");
                    for(int i = 0; i < contests.length();i++){
                        JSONObject c = contests.getJSONObject(i);
                        if(c.get("phase").equals("BEFORE")){
                            //Checkea e ingresa los datos de cada una de las competencias
                            //agrega la competencia a la lista
                            Contest contest = new Contest();
                            contest.setId(c.getInt("id"));
                            contest.setName(c.getString("name"));
                            contest.setType(c.getString("type"));
                            if (!c.isNull("durationSeconds")) contest.setDurationSeconds(c.getInt("durationSeconds"));
                            else contest.setDurationSeconds(null);
                            if (!c.isNull("startTimeSeconds")) contest.setStartTimeSeconds(c.getInt("startTimeSeconds"));
                            else contest.setStartTimeSeconds(null);
                            lista_competencias.add(contest);
                        }
                    }

                    Runnable r1 = new Runnable() {
                        @Override
                        public void run() {
                            Collections.reverse(lista_competencias);
                            adapter = new adaptador_competencia(getActivity(),lista_competencias);
                            lvCompetencias.setAdapter(adapter);
                        }
                    };
                    myHandler.post(r1);
                }
                catch(Exception e) {
                    Log.e("error", e.getMessage());
                }
            }
        });
        thread.start();

        return view;
    }

    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
    }
}
