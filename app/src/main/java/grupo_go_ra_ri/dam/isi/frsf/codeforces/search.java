package grupo_go_ra_ri.dam.isi.frsf.codeforces;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

import grupo_go_ra_ri.dam.isi.frsf.codeforces.dao.CodeForcesDao;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.dao.CodeForcesDaoHTTP;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.dao.MyGenericHTTPClient;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.model.User;


public class search extends Fragment {
    private CodeForcesDao daoCodeForces;
    private MyGenericHTTPClient cliente;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        daoCodeForces = new CodeForcesDaoHTTP();
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        final EditText handle = (EditText)view.findViewById(R.id.et_handle_search);
        Button btnSearch = (Button) view.findViewById(R.id.bt_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cliente = new MyGenericHTTPClient();
                ArrayList<User> users = new ArrayList<User>();
                Thread thread = new Thread(new Runnable(){
                    @Override
                    public void run() {
                        try {
                            String url = "http://codeforces.com/api/user.info?handles="+handle.getText();
//                            String urlString = "http://codeforces.com/api/user.friends?onlyOnline=true";
//                            String userData = cliente.performGetCall(urlString, null);
                            String userData = cliente.performGetCall(url, null);
                            JSONObject obj = new JSONObject(userData);
                            String status = (String) obj.getString("status");
                            if (status.equals("OK")) {
                                //MenuSlideActivity.opcion = 5;
                                Intent intencion = new Intent(getActivity().getApplicationContext(), MenuSlideActivity.class);
                                startActivity(intencion);
                            } else {
                                Looper.prepare();
                                Toast.makeText(getActivity().getApplicationContext(), "No hay ningun usuario con ese handle", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Log.e("error", e.getMessage());
                        }
                    }
                });
                thread.start();
            // Esto está mal, porque se ejecuta el if antes de que la función de arriba retorne el objet
            // usuario. Hay que buscar como se ejecuta algo esperando la respuesta del "Thread" o hilo
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
