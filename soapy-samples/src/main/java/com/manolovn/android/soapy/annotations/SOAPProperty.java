package com.manolovn.android.soapy.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Define a SOAP Method name
 */
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface SOAPProperty {
    String value();
}
