package com.manolovn.android.soapy.sample.conversionrate;

import com.manolovn.android.soapy.annotations.SOAPMethod;
import com.manolovn.android.soapy.annotations.SOAPProperty;

/**
 * Conversion Rate API
 *
 * @author manolovn
 */
public interface ConversionRateApi {

    @SOAPMethod("ConversionRate")
    String conversionRate(@SOAPProperty("FromCurrency") String fromCurrency, @SOAPProperty("ToCurrency") String toCurrency);

}
