package com.manolovn.android.soapy.sample.quote;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import com.manolovn.android.soapy.Soapy;
import com.manolovn.android.soapy.sample.R;

/**
 * Sample with quote api
 *
 * @author manolovn
 */
public class QuoteActivity extends Activity {

    QuoteApi quoteApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Soapy api = new Soapy.Builder()
                .setEndpoint("http://www.webservicex.net/stockquote.asmx")
                .setNamespace("http://www.webserviceX.NET")
                .build();
        quoteApi = api.create(QuoteApi.class);

        new SoapTask().execute("GOOG");
    }

    private class SoapTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            String resultsString = "";
            resultsString = quoteApi.getQuote(params[0]);
            return resultsString;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result == null) {
                Toast.makeText(QuoteActivity.this, "EMPTY RESPONSE", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(QuoteActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
