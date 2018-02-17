package grupo_go_ra_ri.dam.isi.frsf.codeforces;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


import java.util.List;

import grupo_go_ra_ri.dam.isi.frsf.codeforces.dao.CodeForcesDao;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.dao.CodeForcesDaoHTTP;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.model.User;

public class MainActivity extends AppCompatActivity {
    private ListView listViewUser;
    private List<User> listUser;
    // private userAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MenuSlideActivity.opcion=1;

        Intent intencion = new Intent(getApplicationContext(),MenuSlideActivity.class);
        startActivity(intencion);
    }

}