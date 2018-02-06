package grupo_go_ra_ri.dam.isi.frsf.codeforces.dao;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import grupo_go_ra_ri.dam.isi.frsf.codeforces.model.User;

/**
 * Created by hrickert on 14/01/2018.
 */

public class CodeForcesDaoHTTP implements CodeForcesDao {
    private MyGenericHTTPClient cliente;

    @Override
    public User getUserByHandle(String handle) {
        cliente = new MyGenericHTTPClient();
        final String urlString = "http://codeforces.com/api/user.info?handles="+handle;
        final User[] user = {new User()};
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
            try {
                String userData = cliente.performGetCall(urlString, null);
                JSONObject obj = new JSONObject(userData);
                String status = (String) obj.getString("status");
                JSONArray result = (JSONArray) obj.getJSONArray("result");
                String handle = (String) result.getJSONObject(0).getString("handle");
                String email = (String) result.getJSONObject(0).getString("email");
                String vkId = (String) result.getJSONObject(0).getString("vkId");
                String openId = (String) result.getJSONObject(0).getString("openId");
                String firstName = (String) result.getJSONObject(0).getString("firstName");
                String lastName = (String) result.getJSONObject(0).getString("lastName");
                String country = (String) result.getJSONObject(0).getString("country");
                String city = (String) result.getJSONObject(0).getString("city");
                String organization = (String) result.getJSONObject(0).getString("organization");
                String contribution = (String) result.getJSONObject(0).getString("contribution");
                String rank = (String) result.getJSONObject(0).getString("rank");
                String rating = (String) result.getJSONObject(0).getString("rating");
                String maxRank = (String) result.getJSONObject(0).getString("maxRank");
                String maxRating = (String) result.getJSONObject(0).getString("maxRating");
                String lastOnlineTimeSeconds = (String) result.getJSONObject(0).getString("registrationTimeSeconds");
                String registrationTimeSeconds = (String) result.getJSONObject(0).getString("registrationTimeSeconds");
                String friendOfCount = (String) result.getJSONObject(0).getString("friendOfCount");
                String avatar = (String) result.getJSONObject(0).getString("avatar");
                String titlePhoto = (String) result.getJSONObject(0).getString("titlePhoto");
                user[0] = new User(handle, email, vkId, openId, firstName, lastName, country, city,
                    organization, Integer.parseInt(rating), rank, maxRank);
            } catch (Exception e) {
                Log.e("error", e.getMessage());
            }
            }
        });
        thread.start();
        return user[0];
    }

    @Override
    public void create(User u) {}

    @Override
    public void update(User u) {}

    @Override
    public void delete(User u) {}
}

