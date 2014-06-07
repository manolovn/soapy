package com.manolovn.android.soapy;

import com.manolovn.android.soapy.annotations.SOAPMethod;
import com.manolovn.android.soapy.annotations.SOAPProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Soapy builder
 */
public class Soapy {

    final String endpoint;
    final String namespace;

    final HttpTransportSE httpTransportSE;

    private Soapy(String endpoint, String namespace) {
        this.endpoint = endpoint;
        this.namespace = namespace;

        this.httpTransportSE = getHttpTransportSE(endpoint);
    }

    private class SoapyHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            // extract method name
            String methodname = "";
            if (method.isAnnotationPresent(SOAPMethod.class)) {
                System.out.println("SOAP METHOD " + method.getName());

                SOAPMethod methodNameAnnotation = method.getAnnotation(SOAPMethod.class);
                methodname = methodNameAnnotation.value();
            }
            SoapObject request = new SoapObject(namespace + "/", methodname);

            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            Class[] parameterTypes = method.getParameterTypes();

            int i = 0;
            for(Annotation[] annotations : parameterAnnotations){
                Class parameterType = parameterTypes[i++];

                for(Annotation annotation : annotations){
                    if(annotation instanceof SOAPProperty){
                        SOAPProperty myAnnotation = (SOAPProperty) annotation;
                        System.out.println("param: " + parameterType.getName());
                        System.out.println("value: " + myAnnotation.value());
                        request.addProperty(myAnnotation.value(), args[0]);
                    }
                }
            }

            SoapSerializationEnvelope envelope = getSoapSerializationEnvelope(request);
            httpTransportSE.call(namespace + "/" + methodname, envelope);
            //testHttpResponse(httpTransportSE);
            return envelope.getResponse().toString();
        }

    }

    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> apiInterface) {
        return (T) Proxy.newProxyInstance(apiInterface.getClassLoader(), new Class<?>[]{apiInterface}, new SoapyHandler());
    }

    public static class Builder {
        private String endpoint;
        private String namespace;

        public Builder setEndpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public Builder setNamespace(String namespace) {
            this.namespace = namespace;
            return this;
        }

        public Soapy build() {
            if (endpoint == null) {
                throw new IllegalArgumentException("Endpoint may not be null");
            }
            setDefaults();
            return new Soapy(endpoint, namespace);
        }

        private void setDefaults() {
            if (namespace == null) {
                namespace = endpoint;
            }
        }
    }

    /**
     * Serialize {@link org.ksoap2.serialization.SoapObject} to {@link org.ksoap2.serialization.SoapSerializationEnvelope}
     *
     * @param request : SOAP object to be serialized
     * @return : request serialized as SOAP envelope
     */
    protected final SoapSerializationEnvelope getSoapSerializationEnvelope(SoapObject request) {
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.setAddAdornments(false);
        envelope.setOutputSoapObject(request);

        return envelope;
    }

    /**
     * Returns SOAP HTTP transport
     *
     * @param baseUrl : base URL
     * @return {@link org.ksoap2.transport.HttpTransportSE}
     */
    protected final HttpTransportSE getHttpTransportSE(String baseUrl) {
        HttpTransportSE ht = new HttpTransportSE(java.net.Proxy.NO_PROXY, baseUrl, 60000);
        ht.debug = true;
        ht.setXmlVersionTag("<!--?xml version=\"1.0\" encoding= \"UTF-8\" ?-->");
        return ht;
    }

}
