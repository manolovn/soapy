package com.manolovn.android.soapy.weather;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import com.manolovn.android.soapy.R;
import com.manolovn.android.soapy.Soapy;

/**
 * Sample with weather api
 *
 * @author manolovn
 */
public class WeatherActivity extends Activity {

    WeatherApi weaherApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Soapy api = new Soapy.Builder()
                .setEndpoint("http://www.webservicex.net/globalweather.asmx")
                .setNamespace("http://www.webserviceX.NET")
                .build();
        weaherApi = api.create(WeatherApi.class);

        new SoapTask().execute("Madrid", "Spain");
    }

    private class SoapTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            String resultsString = "";
            resultsString = weaherApi.getWeather(params[0], params[1]);
            return resultsString;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result == null) {
                Toast.makeText(WeatherActivity.this, "EMPTY RESPONSE", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(WeatherActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
