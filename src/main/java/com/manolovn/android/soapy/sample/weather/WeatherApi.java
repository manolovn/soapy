package com.manolovn.android.soapy.sample.weather;

import com.manolovn.android.soapy.annotations.SOAPMethod;
import com.manolovn.android.soapy.annotations.SOAPProperty;

/**
 * Weather API
 *
 * @author manolovn
 */
public interface WeatherApi {

    @SOAPMethod("GetWeather")
    String getWeather(@SOAPProperty("CityName") String cityName, @SOAPProperty("CountryName") String countryName);

    @SOAPMethod("GetCitiesByCountry")
    String getCitiesByCountry(@SOAPProperty("CountryName") String countryName);

    @SOAPMethod("GetQuote")
    String getQuote(@SOAPProperty("symbol") String symbol);

}
