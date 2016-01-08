package com.manolovn.android.soapy.weather;

import com.manolovn.android.soapy.annotations.SOAPMethod;
import com.manolovn.android.soapy.annotations.SOAPProperty;

/**
 * Weather API
 *
 * @author manolovn
 */
public interface WeatherApi {

    /**
     * Return weather information of given city
     *
     * @param cityName : city
     * @param countryName : country
     * @return an string with XML information
     */
    @SOAPMethod("GetWeather")
    String getWeather(@SOAPProperty("CityName") String cityName,
                      @SOAPProperty("CountryName") String countryName);

    /**
     * Return cities of given country
     *
     * @param countryName : country
     * @return an string with XML information
     */
    @SOAPMethod("GetCitiesByCountry")
    String getCitiesByCountry(@SOAPProperty("CountryName") String countryName);

}
