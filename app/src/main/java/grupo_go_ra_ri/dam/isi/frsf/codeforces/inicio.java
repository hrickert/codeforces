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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

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
        btnFindUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            cliente = new MyGenericHTTPClient();
            final String urlString = "http://codeforces.com/api/user.info?handles=DmitriyH";
            Thread thread = new Thread(new Runnable(){
                @Override
                public void run() {
                    try { // LAS PROPIEDADES QUE VIENEN SON DISTINTAS. CAMBIARLO. FIJARE EN http://codeforces.com/api/user.info?handles=DmitriyH
                        final String userData = cliente.performGetCall(urlString, null);
                        JSONObject obj = new JSONObject(userData);
                        String status = obj.getString("status");
//                        JSONArray result = (JSONArray) obj.getJSONArray("result");
//                        String handle = (String) result.getJSONObject(0).getString("handle");
                        //String email = "DmitriyH@gmail.com";// (String) result.getJSONObject(0).getString("email");
                        //String vkId = ""; // (String) result.getJSONObject(0).getString("vkId");
//                        final String firstName = (String) result.getJSONObject(0).getString("firstName");
                        //String lastName = (String) result.getJSONObject(0).getString("lastName");
                        //String country = (String) result.getJSONObject(0).getString("country");
                        //String city = (String) result.getJSONObject(0).getString("city");
                        //String organization = (String) result.getJSONObject(0).getString("organization");
                        //String contribution = (String) result.getJSONObject(0).getString("contribution");
                        //String rank = (String) result.getJSONObject(0).getString("rank");
                        //String rating = (String) result.getJSONObject(0).getString("rating");
                        //String maxRank = (String) result.getJSONObject(0).getString("maxRank");
                        //String maxRating = (String) result.getJSONObject(0).getString("maxRating");
                        //String lastOnlineTimeSeconds = (String) result.getJSONObject(0).getString("registrationTimeSeconds");
                        //String registrationTimeSeconds = (String) result.getJSONObject(0).getString("registrationTimeSeconds");
                        //String friendOfCount = (String) result.getJSONObject(0).getString("friendOfCount");
                        //String avatar = (String) result.getJSONObject(0).getString("avatar");
                        //String titlePhoto = (String) result.getJSONObject(0).getString("titlePhoto");
                   //     new User(handle, email, vkId, openId, firstName, lastName, country, city,
                //                organization, Integer.parseInt(rating), rank, maxRank);
                        if(status.equals("OK")) {
                            // Voy a la activity perfil, hay que pasarle el usuario creado
                        // Voy a la activity perfil, hay que pasarle el usuario creado
                        //MenuSlideActivity.opcion=2;


                            Runnable r1 = new Runnable() {
                                @Override
                                public void run() {
                                    MenuListener m = (MenuSlideActivity) getActivity();
                                    m.onShowUserData(userData);
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
