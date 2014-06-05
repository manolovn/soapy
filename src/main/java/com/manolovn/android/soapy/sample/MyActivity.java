package com.manolovn.android.soapy.sample;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import com.manolovn.android.soapy.R;
import com.manolovn.android.soapy.Soapy;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MyActivity extends Activity {

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

        new SoapTask().execute("");
    }

    private class SoapTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            String resultsString = "";
            resultsString = tempConvert.celsiusToFahrenheit("30");

            return resultsString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Toast.makeText(MyActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }

}
