package com.manolovn.android.soapy;

import com.manolovn.android.soapy.api.TempConvert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Soapy test
 */
public class SoapyTest {

    @Test
    public void soapyBuilderTest() {

        Soapy api = new Soapy.Builder()
                .setEndpoint("http://www.w3schools.com/webservices/tempconvert.asmx")
                .setNamespace("http://www.w3schools.com/webservices")
                .build();

        TempConvert tempConvert = api.create(TempConvert.class);

        tempConvert.celsiusToFahrenheit("30");
    }

}
