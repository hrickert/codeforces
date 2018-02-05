package grupo_go_ra_ri.dam.isi.frsf.codeforces;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import grupo_go_ra_ri.dam.isi.frsf.codeforces.dao.CodeForcesDao;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.dao.CodeForcesDaoHTTP;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.model.User;


public class inicio extends Fragment {
    private CodeForcesDao daoCodeForces;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        daoCodeForces = new CodeForcesDaoHTTP();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        Button btnFindUser = (Button) view.findViewById(R.id.bt_find_user);
        btnFindUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = daoCodeForces.getUserByHandle("DmitriyH");
                if(true) { // preguntar si existe el usuario
                    MenuSlideActivity.opcion=2;
                    Intent intencion = new Intent(getActivity().getApplicationContext(),MenuSlideActivity.class);
                    startActivity(intencion);
                }
                else {
                    Toast toast1 = Toast.makeText(getActivity().getApplicationContext(), "No se encontró el usuario", Toast.LENGTH_SHORT);
                    toast1.show();
                }
            }
        });

        return view;
    }
}
