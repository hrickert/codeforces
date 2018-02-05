package grupo_go_ra_ri.dam.isi.frsf.codeforces.dao;
import android.net.Uri;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import grupo_go_ra_ri.dam.isi.frsf.codeforces.model.User;

/**
 * Created by hrickert on 14/01/2018.
 */

public class MyGenericHTTPClient {

    public static String buildURL(String url, Map<String, String> params) {
        Uri.Builder builder = Uri.parse(url).buildUpon();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.appendQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        return builder.build().toString();
    }

    public static String performGetCall(String requestURL, HashMap<String, String> getDataParams) {
        URL url;
        StringBuilder response = new StringBuilder();
        try {
            url = new URL(buildURL(requestURL,getDataParams));

            HttpURLConnection urlConnection  = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-type", "application/json");

            int responseCode = urlConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

            }
            urlConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response.toString();
    }
}
