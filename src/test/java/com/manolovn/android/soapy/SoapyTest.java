package com.manolovn.android.soapy;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Soapy test
 */
public class SoapyTest extends TestCase {

    @Test
    public void soapyBuilderTest() {
        final int expected = 5;
        final int reality = 5;
        assertEquals(expected, reality);
    }

}
