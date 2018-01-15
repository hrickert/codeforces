package grupo_go_ra_ri.dam.isi.frsf.codeforces.dao;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import grupo_go_ra_ri.dam.isi.frsf.codeforces.model.User;

/**
 * Created by hrickert on 14/01/2018.
 */

public class CodeForcesDaoHTTP implements CodeForcesDao {
    private String server;
    private MyGenericHTTPClient cliente;

    public CodeForcesDaoHTTP(){
        server="http://codeforces.com/api";
        cliente = new MyGenericHTTPClient(server);
    }

    public CodeForcesDaoHTTP(String server){
        this.server=server;
        cliente=new MyGenericHTTPClient(server);
    }

    @Override
    public User getUserByHandle(String handle){
        User objResult = new User();
        String estadoJSON = cliente.getByHandle(handle);
        try {
            JSONObject user = new JSONObject(estadoJSON);
            objResult = new User(user.getString("handle"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return objResult;
    }

    @Override
    public void create(User u) {}

    @Override
    public void update(User u) {}

    @Override
    public void delete(User u) {}
}

