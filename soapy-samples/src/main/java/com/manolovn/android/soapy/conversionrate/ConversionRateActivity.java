package com.manolovn.android.soapy.conversionrate;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import com.manolovn.android.soapy.R;
import com.manolovn.android.soapy.Soapy;

/**
 * Sample with conversion api
 *
 * @author manolovn
 */
public class ConversionRateActivity extends Activity {

    ConversionRateApi conversionRateApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Soapy api = new Soapy.Builder()
                .setEndpoint("http://www.webservicex.net/CurrencyConvertor.asmx")
                .setNamespace("http://www.webserviceX.NET")
                .build();
        conversionRateApi = api.create(ConversionRateApi.class);

        new SoapTask().execute("USD", "EUR");
    }

    private class SoapTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            String resultsString = "";
            resultsString = conversionRateApi.conversionRate(params[0], params[1]);
            return resultsString;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result == null) {
                Toast.makeText(ConversionRateActivity.this, "EMPTY RESPONSE", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ConversionRateActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
