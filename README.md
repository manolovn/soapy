# Soapy
Consume unfashionable SOAP webservices in a fashionable way, very inspired by Retrofit

# How to use
Define your SOAP API as a Java interface

    public interface LabsApi {
    
        @SOAPMethod("getLab")
        Lab getLab(@SOAPProperty("labid") int labId);
    
    }

Build a Soapy object and create an implementation of your webservice

    Soapy api = new Soapy.Builder()
        .setEndpoint("http://ulabs.science.ubc.ca/pub/interface/ws_server.php")
        .build();
    LabsApi labsApi = api.create(LabsApi.class);

Each call to the generated implementation of your interface makes a request and parse
the response to the desired type

    Lab lab = labsApi.getLab(1);

