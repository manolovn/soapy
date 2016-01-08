package com.manolovn.android.soapy.tempconvert;

import com.manolovn.android.soapy.annotations.SOAPMethod;
import com.manolovn.android.soapy.annotations.SOAPProperty;

/**
 * TempConvert API interface
 *
 * @author manolovn
 */
public interface TempConvert {

    /**
     * Fahrenheit to celsius conversion
     *
     * @param fahrenheits : Fahrenheit degrees
     * @return : Celsius degrees conversion
     */
    @SOAPMethod("FahrenheitToCelsius")
    String fahrenheitToCelsius(@SOAPProperty("Fahrenheit") String fahrenheits);

    /**
     * Celsius to fahrenheit conversion
     *
     * @param celsius : celsius degrees
     * @return : Fahrenheit degrees conversion
     */
    @SOAPMethod("CelsiusToFahrenheit")
    String celsiusToFahrenheit(@SOAPProperty("Celsius") String celsius);

}
