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

import java.util.ArrayList;

import grupo_go_ra_ri.dam.isi.frsf.codeforces.R;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.model.Contest;

public class adaptador_competencia extends BaseAdapter{
    protected Activity activity;
    private ArrayList<Contest> contestList;

    public adaptador_competencia(Activity activity, ArrayList<Contest> contestList) {
        this.activity = activity;
        this.contestList = contestList;
    }

    @Override
    public int getCount() { return contestList.size(); }

    @Override
    public Object getItem(int position) {
        return contestList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void addAll(ArrayList<Contest> lista){
        for(int i=0 ; i<lista.size(); i++){
            contestList.add(lista.get(i));
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        if (convertView==null){
            LayoutInflater inf=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inf.inflate(R.layout.item_contest,null);
        }

        Contest item = contestList.get(position);

        //String imagen = (String)convertView.findViewById(R.id.img_profile_row);
        //imagen= item.getAvatar();
        TextView name = (TextView)v.findViewById(R.id.tvc_name);
        name.setText(item.getName());
        ImageView calendar = (ImageView)v.findViewById(R.id.img_fecha);
        //ImageView alarm = (ImageButton)

        return v;
    }

    public void clear(){
        contestList.clear();
    }
}
