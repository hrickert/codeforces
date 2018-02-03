package grupo_go_ra_ri.dam.isi.frsf.codeforces.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import grupo_go_ra_ri.dam.isi.frsf.codeforces.R;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.model.User;


public class adaptador_usuario extends BaseAdapter{
    protected Activity activity;
    private ArrayList<User> userList;

    public adaptador_usuario(Activity activity, ArrayList<User> userList) {
        this.activity = activity;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void addAll(ArrayList<User> lista){
        for(int i=0 ; i<lista.size(); i++){
            userList.add(lista.get(i));
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        if (convertView==null){
            LayoutInflater inf=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inf.inflate(R.layout.item_user,null);
        }

        final User item = userList.get(position);

        //Instanciamos botones y cargamos el listview con los datos correspondientes a cada competencia
        TextView handle = (TextView)v.findViewById(R.id.tv_handle_row);
        handle.setText(item.getHandle());

        String nombre = new String();
        TextView name = (TextView)v.findViewById(R.id.tv_name_row);
        if (item.getFirstName()==null) {
            if (item.getLastName()== null) nombre = "No name";
            else nombre = item.getLastName();
        }
        else if (item.getLastName()== null) nombre = item.getFirstName();
        else nombre = item.getFirstName() + " " +item.getLastName();
        name.setText(nombre);

        TextView rating = (TextView)v.findViewById(R.id.tv_rating_row);
        rating.setText("(" + item.getRating().toString() + ")");

        ImageButton addFriend = (ImageButton)v.findViewById(R.id.img_addFriend);
        addFriend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Enviamos una solicitud de amistad al usuario, en este caso solo mostramos un toast
                Toast.makeText(activity.getApplicationContext(), "Solicitud de amistad enviada a " + item.getHandle(), Toast.LENGTH_SHORT).show();
            }
        });
        ImageView avatar = (ImageView)v.findViewById(R.id.img_profile_row);
        avatar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Vemos el perfil completo del usuario, en este caso solo mostramos un toast
                Toast.makeText(activity.getApplicationContext(), "Ver perfil de " + item.getHandle(), Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    public void clear(){userList.clear();}
}
