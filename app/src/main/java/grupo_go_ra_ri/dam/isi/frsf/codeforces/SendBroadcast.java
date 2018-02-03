package grupo_go_ra_ri.dam.isi.frsf.codeforces;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

public class SendBroadcast extends AppCompatActivity {

    public void broadcastIntent(String mensaje)
    {
        Intent intent = new Intent();
        intent.setAction("CodeForcesMobile");
        intent.putExtra(mensaje,0);
        sendBroadcast(intent);
    }
}