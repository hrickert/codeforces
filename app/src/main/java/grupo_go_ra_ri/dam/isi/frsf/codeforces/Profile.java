package grupo_go_ra_ri.dam.isi.frsf.codeforces;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import grupo_go_ra_ri.dam.isi.frsf.codeforces.model.RatingChange;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.model.User;


public class Profile extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        try {
            User usr = parseJsonToUser(getArguments().getString("json"), getArguments().getString("rating"));
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

            LineChart chart = view.findViewById(R.id.chart);
            List<Entry> entries = new ArrayList<Entry>();
            for (RatingChange rtChg: usr.getRatingChanges()) {
                entries.add(new Entry(rtChg.getRatingUpdateTimeSeconds(), rtChg.getRank()));
            }
            LineDataSet dataSet = new LineDataSet(entries, usr.getHandle());
            dataSet.setColor(Color.rgb(239,190,190));
            dataSet.setCircleColor(Color.rgb(239,190,190));// add entries to dataset
//            dataSet.setColor();
//            dataSet.setValueTextColor(...);
            LineData lineData = new LineData(dataSet);
            chart.setData(lineData);
            chart.setBackgroundColor(Color.rgb(190, 239, 239));
            chart.setVisibleYRange(0, 4000, chart.getAxisRight().getAxisDependency());
            chart.invalidate();
            chart.fitScreen();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

    private User parseJsonToUser(String jsonString, String ratingString) throws JSONException {
        JSONObject usrJson = new JSONObject(jsonString);
        User usr = new User(usrJson);
        usr.setRatingChanges(new JSONArray(ratingString));
        return usr;
    }
}