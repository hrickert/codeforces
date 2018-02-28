package grupo_go_ra_ri.dam.isi.frsf.codeforces;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

import grupo_go_ra_ri.dam.isi.frsf.codeforces.model.User;


public class Profile extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        try {
            User usr = parseJsonToUser(getArguments().getString("json"));
            TextView tv_user = view.findViewById(R.id.tv_user);
            tv_user.setText(usr.getHandle());
            TextView tv_rating = view.findViewById(R.id.tv_rating);
            tv_rating.setText(usr.getRating().toString());
            TextView tv_rank = view.findViewById(R.id.tv_rango);
            tv_rank.setText(usr.getRank());
            TextView tv_friends = view.findViewById(R.id.tv_friends);
            tv_friends.setText("Amigos: "+usr.getFriendOfCount().toString());
            TextView tv_last_conection = view.findViewById(R.id.tv_last_conection);
            tv_last_conection.setText("Ultima visita: "+usr.getLastOnlineTimeSeconds().toString());
            TextView tv_register = view.findViewById(R.id.tv_register);
            tv_register.setText("Registrado: "+usr.getRegistrationTimeSeconds().toString());
            ImageView iv_avatar = view.findViewById(R.id.img_profile);
            Ion.with(iv_avatar).load(usr.getAvatar());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

    private User parseJsonToUser(String jsonString) throws JSONException {
        JSONObject usrJson = new JSONObject(jsonString);
        User usr = new User(usrJson);

        return usr;
    }
}