package grupo_go_ra_ri.dam.isi.frsf.codeforces;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;

import grupo_go_ra_ri.dam.isi.frsf.codeforces.adaptadores.adaptador_usuario;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.model.User;


public class users_list extends Fragment {

    private ListView lvUsuarios;
    private ArrayList<User> lista_usuarios;
    private adaptador_usuario listAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users, container, false);

        lvUsuarios = (ListView)view.findViewById(R.id.lv_users);

        //Se carga la lista con las competencias
        lista_usuarios = GetArrayItems();

        listAdapter = new adaptador_usuario(getActivity(),lista_usuarios);
        lvUsuarios.setAdapter(listAdapter);

        return view;
    }

    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
    }

    private ArrayList<User> GetArrayItems(){
        ArrayList<User> lista_usuarios = new ArrayList<>();
        User usuario1 = new User();
        User usuario2 = new User();
        User usuario3 = new User();
        User usuario4 = new User();

        usuario1.setHandle("Usuario1");
        usuario1.setRating(1234);
        usuario2.setHandle("Usuario2");
        usuario2.setRating(1000);
        usuario3.setHandle("Usuario3");
        usuario3.setRating(100);
        usuario4.setHandle("Usuario4");
        usuario4.setRating(90);

        lista_usuarios.add(usuario1);
        lista_usuarios.add(usuario2);
        lista_usuarios.add(usuario3);
        lista_usuarios.add(usuario4);

        return lista_usuarios;
    }

}
