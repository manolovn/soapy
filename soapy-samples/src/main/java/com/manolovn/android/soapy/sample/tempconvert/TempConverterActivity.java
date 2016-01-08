package com.manolovn.android.soapy.sample.tempconvert;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import com.manolovn.android.soapy.Soapy;
import com.manolovn.android.soapy.sample.R;

public class TempConverterActivity extends Activity {

    TempConvert tempConvert;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Soapy api = new Soapy.Builder()
                .setEndpoint("http://www.w3schools.com/webservices/tempconvert.asmx")
                .setNamespace("http://www.w3schools.com/webservices")
                .build();
        tempConvert = api.create(TempConvert.class);

        new SoapTask().execute("30");
    }

    private class SoapTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            String resultsString = "";
            resultsString = tempConvert.celsiusToFahrenheit(params[0]);

            return resultsString;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Toast.makeText(TempConverterActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    }

}
