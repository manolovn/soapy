package com.manolovn.android.soapy.sample.lab;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import com.manolovn.android.soapy.R;
import com.manolovn.android.soapy.Soapy;

/**
 * Sample with ubc labs api
 *
 * @author manolovn
 */
public class LabActivity extends Activity {

    LabsApi labsApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Soapy api = new Soapy.Builder()
                .setEndpoint("http://ulabs.science.ubc.ca/pub/interface/ws_server.php")
                .build();
        labsApi = api.create(LabsApi.class);

        new SoapTask().execute(1);
    }

    private class SoapTask extends AsyncTask<Integer, Integer, Lab> {

        @Override
        protected Lab doInBackground(Integer... params) {

            Lab lab = null;
            lab = labsApi.getLab(params[0]);
            return lab;
        }

        @Override
        protected void onPostExecute(Lab lab) {
            super.onPostExecute(lab);

            Toast.makeText(LabActivity.this, lab.getTitle(), Toast.LENGTH_SHORT).show();
        }
    }

}
