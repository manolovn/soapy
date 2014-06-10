package com.manolovn.android.soapy.sample.conversionrate;

import com.manolovn.android.soapy.annotations.SOAPMethod;
import com.manolovn.android.soapy.annotations.SOAPProperty;

/**
 * Conversion Rate API
 *
 * @author manolovn
 */
public interface ConversionRateApi {

    /**
     * Returns a SoapPrimitive as String
     *
     * @param fromCurrency Currency code
     * @param toCurrency Currency code
     * @return A representation of the conversion rate between currencies
     */
    @SOAPMethod("ConversionRate")
    String conversionRate(@SOAPProperty("FromCurrency") String fromCurrency, @SOAPProperty("ToCurrency") String toCurrency);

}
