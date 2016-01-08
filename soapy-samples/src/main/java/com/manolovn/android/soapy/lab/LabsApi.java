package com.manolovn.android.soapy.lab;

import com.manolovn.android.soapy.annotations.SOAPMethod;
import com.manolovn.android.soapy.annotations.SOAPProperty;

/**
 * Labs sample API
 *
 * @author manolovn
 */
public interface LabsApi {

    /**
     * Returns a complex type as SoapObject parsed to domain object
     * @param labId : laboratory id
     * @return : Domain Lab object
     */
    @SOAPMethod("getLab")
    Lab getLab(@SOAPProperty("labid") int labId);

}
