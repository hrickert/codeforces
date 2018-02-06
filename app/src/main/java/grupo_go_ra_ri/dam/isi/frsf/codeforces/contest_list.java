package grupo_go_ra_ri.dam.isi.frsf.codeforces;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import grupo_go_ra_ri.dam.isi.frsf.codeforces.adaptadores.adaptador_competencia;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.dao.CodeForcesDaoHTTP;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.dao.MyGenericHTTPClient;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.model.Contest;


public class contest_list extends Fragment {

    private ListView lvCompetencias;
    private ArrayList<Contest> lista_competencias = new ArrayList<>();
    private adaptador_competencia adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contest, container, false);

        lvCompetencias = (ListView)view.findViewById(R.id.lv_contest);

        //Se carga la lista con los las competencias
        //lista_competencias = GetArrayItems();
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    MyGenericHTTPClient cliente = new MyGenericHTTPClient();
                    final String urlString = "http://codeforces.com/api/contest.list?gym=false";
                    String stringOfContests = cliente.performGetCall(urlString, null);
                    System.out.println(stringOfContests);
                    JSONObject obj = new JSONObject(stringOfContests);
                    JSONArray contests = obj.getJSONArray("result");
                    for(int i = 0; i < contests.length();i++){
                        JSONObject c = contests.getJSONObject(i);
                        System.out.println(c.get("phase"));
                        if(c.get("phase").equals("BEFORE")){
                            lista_competencias.add(new Contest( c.getInt("id"), c.getString("name"),
                                    c.getBoolean("frozen"), c.getInt("durationSeconds"),
                                    c.getInt("startTimeSeconds"), c.getInt("relativeTimeSeconds"),
                                    c.getString("type")));
                        }
                    }
                    System.out.println(lista_competencias);
                    adapter = new adaptador_competencia(getActivity(),lista_competencias);
                    lvCompetencias.setAdapter(adapter);
                }
                catch(Exception e) {
                    Log.e("error", e.getMessage());
                }
            }
        });
        thread.start();

        //adapter = new adaptador_competencia(getActivity(),lista_competencias);
        //lvCompetencias.setAdapter(adapter);


        return view;
    }


    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
    }

    private ArrayList<Contest> GetArrayItems(){
        final ArrayList<Contest> lista_competencias = new ArrayList<>();
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    MyGenericHTTPClient cliente = new MyGenericHTTPClient();
                    final String urlString = "http://codeforces.com/api/contest.list?gym=false";
                    String stringOfContests = cliente.performGetCall(urlString, null);
                    System.out.println(stringOfContests);
                    JSONObject obj = new JSONObject(stringOfContests);
                    JSONArray contests = obj.getJSONArray("result");
                    for(int i = 0; i < contests.length();i++){
                        JSONObject c = contests.getJSONObject(i);
                        System.out.println(c.get("phase"));
                        if(c.get("phase").equals("BEFORE")){
                                lista_competencias.add(new Contest( c.getInt("id"), c.getString("name"),
                                c.getBoolean("frozen"), c.getInt("durationSeconds"),
                                c.getInt("startTimeSeconds"), c.getInt("relativeTimeSeconds"),
                                c.getString("type")));
                        }
                    }
                    System.out.println(lista_competencias);
                }
                catch(Exception e) {
                    Log.e("error", e.getMessage());
                }
            }
        });
        thread.start();
        return lista_competencias;
    }
}
