package grupo_go_ra_ri.dam.isi.frsf.codeforces;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        String mensaje = intent.getExtras().getString("mensaje");
        Toast.makeText(context, "Intent Detected. " + mensaje, Toast.LENGTH_LONG).show();
    }
}