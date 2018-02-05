package grupo_go_ra_ri.dam.isi.frsf.codeforces.adaptadores;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import grupo_go_ra_ri.dam.isi.frsf.codeforces.R;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.SendBroadcast;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.model.Contest;

import static android.content.Context.NOTIFICATION_SERVICE;


public class adaptador_competencia extends BaseAdapter{
    private Activity activity;
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
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View v=convertView;
        final Context context = activity.getApplicationContext();

        if (convertView==null){
            LayoutInflater inf=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inf.inflate(R.layout.item_contest,null);
        }

        //Obtenemos el item correspondiente a la fila del listview
        final Contest item = contestList.get(position);
        final String nombre = item.getName();

        //Instanciamos botones y cargamos el listview con los datos correspondientes a cada competencia
        TextView name = (TextView) v.findViewById(R.id.tvc_name);
        name.setText(nombre);
        TextView dificultad = (TextView) v.findViewById(R.id.tvc_difficult);
        if (item.getDifficulty() == null) dificultad.setText("--");
        else dificultad.setText("Dificultad: " + item.getDifficulty().toString());

        TextView comienzo = (TextView) v.findViewById(R.id.tvc_start);
        if (item.getStartTimeSeconds() == null) comienzo.setText("--");
        else comienzo.setText("Comienza en: " + item.getStartTimeSeconds().toString());

        TextView creador = (TextView) v.findViewById(R.id.tvc_preparedBy);
        if (item.getPreparedBy() == null) creador.setText("--");
        else creador.setText("Organizado por: " + item.getPreparedBy());


        ImageButton info = (ImageButton) v.findViewById(R.id.img_info);
        ImageButton calendar = (ImageButton) v.findViewById(R.id.img_fecha);
        final ImageButton alarm = (ImageButton) v.findViewById(R.id.img_alarma);
        ImageButton share = (ImageButton) v.findViewById(R.id.img_compartir);

        info.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Invoca una nueva actividad con la informacion completa de la competencia,
                //mostramos el mensaje para la prueba
                Toast.makeText(context, "Muestra la información completa de competencia " + nombre, Toast.LENGTH_SHORT).show();
            }
        });
        calendar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Mostramos un mensaje a modo de prueba, este boton permite agregar la competencia en
                //el calendario personal del usuario
                Toast.makeText(context, "La competencia '" + nombre + "' se agregó a su calendario personal.", Toast.LENGTH_SHORT).show();
            }
        });
        alarm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
/*                SendBroadcast enviar = new SendBroadcast();
                enviar.broadcastIntent(item.getName());*/

                //Lanzamos una notificacion a modo de simulacion de lo que sucede luego del paso del tiempo,
                //en la app completa deberia anotarse en la competencia e informar al usuario
                NotificationCompat.Builder mBuilder;
                NotificationManager mNotifyMgr =(NotificationManager)activity.getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
                Intent i=new Intent(activity,adaptador_competencia.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(activity, 0, i, 0);

                String contenido = "Has sido anotado";
                //Estructura de la notificacion
                mBuilder =new NotificationCompat.Builder(activity.getApplicationContext())
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(R.drawable.ic_notif)
                        .setContentTitle("CodeForces Mobile")
                        .setContentText(contenido)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(contenido + " para participar en la Competencia: " + nombre))
                        .setAutoCancel(true);
                alarm.setEnabled(false);
                alarm.setBackgroundColor(Color.parseColor("#000000"));
                mNotifyMgr.notify(0, mBuilder.build());
            }
        });
        share.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Comparte la competencia permitiendo que el usuario elija la app que prefiera
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Me anote para participar en la competencia " + nombre +" de CodeForces ¡¡Animate vos tambien!!");
                activity.startActivity(Intent.createChooser(intent, "Compartir " + nombre + " con:"));
            }
        });

        return v;
    }

    public void clear(){
        contestList.clear();
    }
}
