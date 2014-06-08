package com.manolovn.android.soapy.api;

import com.manolovn.android.soapy.annotations.SOAPMethod;
import com.manolovn.android.soapy.annotations.SOAPProperty;

/**
 * Labs sample API
 *
 * @author manolovn
 */
public interface LabsApi {

    @SOAPMethod("getLab")
    Lab getLab(@SOAPProperty("labid") int labId);

}
