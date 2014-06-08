package com.manolovn.android.soapy.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Define a SOAP Method name
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface SOAPMethod {
    String value();
}
