package com.manolovn.android.soapy;

import com.manolovn.android.soapy.api.Lab;
import com.manolovn.android.soapy.api.LabsApi;
import com.manolovn.android.soapy.api.TempConvert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Soapy test
 *
 * @author manolovn
 */
public class SoapyTest {

    @Test(expected = IllegalArgumentException.class)
    public void initilizactionFailTest() {
        Soapy api = new Soapy.Builder().build();
    }

    @Test
    public void generalTest() {
        LabsApi labsApi;
        Soapy api = new Soapy.Builder()
                .setEndpoint("http://ulabs.science.ubc.ca/pub/interface/ws_server.php")
                .build();
        labsApi = api.create(LabsApi.class);

        Lab lab = null;
        lab = labsApi.getLab(1);

        assertEquals(1, lab.getId());
    }

    @Test
    public void primitiveTest() {
        TempConvert tempConvert;
        Soapy api = new Soapy.Builder()
                .setEndpoint("http://www.w3schools.com/webservices/tempconvert.asmx")
                .setNamespace("http://www.w3schools.com/webservices")
                .build();
        tempConvert = api.create(TempConvert.class);

        String result = tempConvert.celsiusToFahrenheit("30");

        assertEquals("86", result);
        assertNotSame(86, result);
    }

}
