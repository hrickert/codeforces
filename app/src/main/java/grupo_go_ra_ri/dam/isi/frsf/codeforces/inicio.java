package grupo_go_ra_ri.dam.isi.frsf.codeforces;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintStream;

import grupo_go_ra_ri.dam.isi.frsf.codeforces.dao.CodeForcesDao;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.dao.CodeForcesDaoHTTP;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.dao.MyGenericHTTPClient;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.model.User;


public class inicio extends Fragment {
    private CodeForcesDao daoCodeForces;
    private MyGenericHTTPClient cliente;

    private Handler myHandler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        daoCodeForces = new CodeForcesDaoHTTP();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        Button btnFindUser = (Button) view.findViewById(R.id.bt_find_user);
        final EditText username = (EditText) view.findViewById(R.id.et_handle);
        btnFindUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            cliente = new MyGenericHTTPClient();
            final String urlString = "http://codeforces.com/api/user.info?handles="+username.getText().toString();
            Thread thread = new Thread(new Runnable(){
                @Override
                public void run() {
                    try { // LAS PROPIEDADES QUE VIENEN SON DISTINTAS. CAMBIARLO. FIJARE EN http://codeforces.com/api/user.info?handles=DmitriyH
                        final String userData = cliente.performGetCall(urlString, null);
                        final JSONObject obj = new JSONObject(userData);
                        String ratingUrlString = "http://codeforces.com/api/user.rating?handle="+username.getText().toString();
                        final String ratingChangesString = cliente.performGetCall(ratingUrlString, null);
                        String status = obj.getString("status");
                        if(status.equals("OK")) {
                            JSONObject ratings = new JSONObject(ratingChangesString);
                            JSONArray ratingChanges = ratings.getJSONArray("result");
//                            System.out.println(ratingChanges.toString());
                            Runnable r1 = new Runnable() {
                                @Override
                                public void run() {
                                    MenuListener m = (MenuSlideActivity) getActivity();
                                    try {
                                        m.onShowUserData(obj.getJSONArray("result").get(0).toString());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                            myHandler.post(r1);
                        }else{
                            Toast error = Toast.makeText(getActivity(),obj.getString("comment"), Toast.LENGTH_LONG);
                            error.show();
                        }

                    } catch (Exception e) {
                        Log.e("error", e.getMessage());
                    }
                }
            });
            thread.start();
            }
        });

        return view;
    }
}
