package grupo_go_ra_ri.dam.isi.frsf.codeforces.adaptadores;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import grupo_go_ra_ri.dam.isi.frsf.codeforces.R;
import grupo_go_ra_ri.dam.isi.frsf.codeforces.model.Contest;

import static android.content.Context.NOTIFICATION_SERVICE;


public class adaptador_competencia extends BaseAdapter{
    private Activity activity;
    private ArrayList<Contest> contestList;

    //Permisos para escribir y leer en la tarjeta de memoria
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

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
    public View getView(final int position, View convertView, ViewGroup parent) {
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
        TextView tipo = (TextView) v.findViewById(R.id.tvc_type);
        tipo.setText("Tipo: " + item.getType().toString());

        TextView comienzo = (TextView) v.findViewById(R.id.tvc_start);
        if (item.getStartTimeSeconds() == null) comienzo.setText("--");
        else comienzo.setText("Comienzo: " + segundosADate(item.getStartTimeSeconds()));

        TextView duracion = (TextView) v.findViewById(R.id.tvc_duration);
        if (item.getDurationSeconds() == null) duracion.setText("--");
        else duracion.setText("Duracion: " + segundosAHoras(item.getDurationSeconds()));


        ImageButton info = (ImageButton) v.findViewById(R.id.img_info);
        final ImageButton calendar = (ImageButton) v.findViewById(R.id.img_fecha);
        final ImageButton alarm = (ImageButton) v.findViewById(R.id.img_alarma);
        ImageButton share = (ImageButton) v.findViewById(R.id.img_compartir);

        info.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Invoca una nueva actividad con la informacion completa de la competencia,
                //mostramos el mensaje para esta entrega
                Toast.makeText(context, "Muestra la información completa de competencia " + nombre, Toast.LENGTH_SHORT).show();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Mostramos un mensaje a modo de prueba, este boton permite agregar la competencia en
                //el calendario personal del usuario
                saveOnFile(item);
                addEventToCalendar(item);
                Toast.makeText(context, "La competencia '" + nombre + "' se agregó a su calendario personal.", Toast.LENGTH_SHORT).show();
            }
        });

        alarm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
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


    // Conversores
    private String segundosADate(long num){
        Date date = new Date(num*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-3"));
        return sdf.format(date);
    }

    private String segundosAHoras(int num){
        int dias,hor,min;
        hor=num/3600;
        if (hor > 24) {
            dias = hor / 24;
            hor = hor - (dias * 24);
            min=(num-((3600*hor)+(dias*24*3600)))/60;
            return dias + "d "+hor+"h "+min+"m";
        }else {
            min = (num - (3600 * hor)) / 60;
            return hor + "h " + min + "m";
        }
    }


    private void addEventToCalendar(Contest evento){
        Calendar cal = Calendar.getInstance();

        String fecha = segundosADate(evento.getStartTimeSeconds());
        int dia = Integer.parseInt(fecha.substring(0,2));
        int mes = Integer.parseInt(fecha.substring(3,5));
        int anio = Integer.parseInt(fecha.substring(6,8));
        int hora = Integer.parseInt(fecha.substring(9,11));
        int min = Integer.parseInt(fecha.substring(12,14));

        cal.set(Calendar.DAY_OF_MONTH, dia);
        cal.set(Calendar.MONTH, mes-1);
        cal.set(Calendar.YEAR, anio+2000);
        cal.set(Calendar.HOUR_OF_DAY, hora);
        cal.set(Calendar.MINUTE, min);

        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, cal.getTimeInMillis()+60*60*1000);
        intent.putExtra(CalendarContract.Events.ALL_DAY, false);
        intent.putExtra(CalendarContract.Events.TITLE, evento.getName());

        activity.startActivity(intent);
    }


    //Guarda el evento en un archivo local de la tarjeta SD
    private void saveOnFile(Contest evento) {
        //Checkeamos si tenemos los permisos
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // No lo tenemos, entonces preguntamos al usuario si da los permisos
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
        try{
            // El archivo se guarda en la carpeta Downloads
            File ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File f = new File(ruta, "calendario.txt");
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f));
            osw.write("El usuario fue anotado a la competencia '"+evento.getName()+"' que se realizara en fecha y hora: "+segundosADate(evento.getStartTimeSeconds())+'\n'+'\n');
            osw.flush();
            osw.close();

            Toast.makeText(activity.getApplicationContext(), "Los datos fueron grabados correctamente", Toast.LENGTH_SHORT).show();

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void clear(){
        contestList.clear();
    }
}
