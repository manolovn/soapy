package com.manolovn.android.soapy.sample.quote;

import com.manolovn.android.soapy.annotations.SOAPMethod;
import com.manolovn.android.soapy.annotations.SOAPProperty;

/**
 * Quote API
 *
 * @author manolovn
 */
public interface QuoteApi {

    /**
     * Returns quote information of given company
     *
     * @param symbol : Company code
     * @return : An string with XML
     */
    @SOAPMethod("GetQuote")
    String getQuote(@SOAPProperty("symbol") String symbol);

}
