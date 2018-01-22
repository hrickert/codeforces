package grupo_go_ra_ri.dam.isi.frsf.codeforces.adaptadores;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

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

        User item = userList.get(position);

        //String imagen = (String)convertView.findViewById(R.id.img_profile_row);
        //imagen= item.getAvatar();
        TextView handle = (TextView)v.findViewById(R.id.tv_handle_row);
        handle.setText(item.getHandle());
        /*TextView name = (TextView)v.findViewById(R.id.tv_name_row);
        String nombre = item.getFirstName();
        nombre = nombre + " " + item.getLastName();
        name.setText(nombre);
        TextView rating = (TextView)v.findViewById(R.id.tv_rating_row);
        rating.setText(item.getRating());
        CheckBox checkBox = (CheckBox)v.findViewById(R.id.cb_row);*/

        return v;
    }

    public void clear(){userList.clear();}
}
