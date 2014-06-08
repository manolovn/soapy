package com.manolovn.android.soapy;

import android.util.Log;
import com.manolovn.android.soapy.annotations.SOAPMethod;
import com.manolovn.android.soapy.annotations.SOAPProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
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
    final SoapSerializationEnvelope envelope;

    private Soapy(String endpoint, String namespace, HttpTransportSE httpTransportSE, SoapSerializationEnvelope envelope) {
        this.endpoint = endpoint;
        this.namespace = namespace;
        this.httpTransportSE = httpTransportSE;
        this.envelope = envelope;
    }

    private class SoapyHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            // extract method name
            String methodname = "";
            if (method.isAnnotationPresent(SOAPMethod.class)) {
                SOAPMethod methodNameAnnotation = method.getAnnotation(SOAPMethod.class);
                methodname = methodNameAnnotation.value();
            }
            SoapObject request = new SoapObject(namespace + "/", methodname);

            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            Class[] parameterTypes = method.getParameterTypes();

            int i = 0;
            int k = 0;
            for (Annotation[] annotations : parameterAnnotations) {
                Class parameterType = parameterTypes[i++];

                for (Annotation annotation : annotations) {
                    if (annotation instanceof SOAPProperty) {
                        SOAPProperty myAnnotation = (SOAPProperty) annotation;
                        request.addProperty(myAnnotation.value(), args[k]);
                        k++;
                    }
                }
            }

            envelope.setOutputSoapObject(request);
            try {
                httpTransportSE.call(namespace + "/" + methodname, envelope);
            } catch (Exception e) {
                Log.e("xxx", e.getMessage());
            }

            if (envelope.getResponse() instanceof SoapPrimitive) {
                SoapPrimitive primitive = (SoapPrimitive) envelope.getResponse();
                return primitive.toString();
            }

            if (envelope.getResponse() instanceof SoapObject) {
                Class<?> returnType = method.getReturnType();
                SoapObject response = (SoapObject) envelope.getResponse();

                // parse SoapObject to return type
                Object instance = null;
                instance = returnType.newInstance();
                returnType.cast(instance);

                int totalCount = response.getPropertyCount();
                for (int detailCount = 0; detailCount < totalCount; detailCount++) {

                    PropertyInfo propertyInfo = new PropertyInfo();
                    response.getPropertyInfo(detailCount, propertyInfo);

                    try {
                        Field field = instance.getClass().getDeclaredField(propertyInfo.getName());
                        field.setAccessible(true);
                        field.set(instance, propertyInfo.getValue());
                    } catch (NoSuchFieldException exception) {
                        // nevermind
                    }
                }

                return instance;
            }

            return null;
        }

    }

    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> apiInterface) {
        return (T) Proxy.newProxyInstance(apiInterface.getClassLoader(), new Class<?>[]{apiInterface}, new SoapyHandler());
    }

    public static class Builder {
        private String endpoint;
        private String namespace;
        private SoapSerializationEnvelope envelope;
        private HttpTransportSE httpTransportSE;

        public Builder setEndpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public Builder setNamespace(String namespace) {
            this.namespace = namespace;
            return this;
        }

        public Builder setEnvelope(SoapSerializationEnvelope envelope) {
            this.envelope = envelope;
            return this;
        }

        public Builder setHttpTransportSE(HttpTransportSE httpTransportSE) {
            this.httpTransportSE = httpTransportSE;
            return this;
        }

        public Soapy build() {
            if (endpoint == null) {
                throw new IllegalArgumentException("Endpoint may not be null");
            }
            setDefaults();
            return new Soapy(endpoint, namespace, httpTransportSE, envelope);
        }

        private void setDefaults() {
            if (namespace == null) {
                namespace = endpoint;
            }
            if (httpTransportSE == null) {
                httpTransportSE = new HttpTransportSE(java.net.Proxy.NO_PROXY, endpoint, 60000);
                httpTransportSE.setXmlVersionTag("<!--?xml version=\"1.0\" encoding= \"UTF-8\" ?-->");
            }
            if (envelope == null) {
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
            }
        }
    }

}
