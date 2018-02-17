package grupo_go_ra_ri.dam.isi.frsf.codeforces;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import grupo_go_ra_ri.dam.isi.frsf.codeforces.model.User;


public class Profile extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView tv = view.findViewById(R.id.tv_user);
        tv.setText(getArguments().getString("aaaa"));
        return view;

    }

    private User parseJsonToUser(String jsonString) throws JSONException {
        JSONObject usrJson = new JSONObject(jsonString);
        User usr = new User();
        return new User();
    }
}