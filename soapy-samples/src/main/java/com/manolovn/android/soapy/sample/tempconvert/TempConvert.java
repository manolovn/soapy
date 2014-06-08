package com.manolovn.android.soapy.sample.tempconvert;

import com.manolovn.android.soapy.annotations.SOAPMethod;
import com.manolovn.android.soapy.annotations.SOAPProperty;

/**
 * TempConvert API interface
 *
 * @author manolovn
 */
public interface TempConvert {

    @SOAPMethod("FahrenheitToCelsius")
    String fahrenheitToCelsius(@SOAPProperty("Fahrenheit") String f);

    @SOAPMethod("CelsiusToFahrenheit")
    String celsiusToFahrenheit(@SOAPProperty("Celsius") String celsius);

}
