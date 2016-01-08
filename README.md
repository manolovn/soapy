# Soapy
Consume unfashionable SOAP webservices in a fashionable way, very inspired by Retrofit

# How to use
Define your SOAP API as a Java interface

```java
    public interface LabsApi {
    
        @SOAPMethod("getLab")
        Lab getLab(@SOAPProperty("labid") int labId);
    
    }
```

Build a Soapy object and create an implementation of your webservice

```java
    Soapy api = new Soapy.Builder()
        .setEndpoint("http://ulabs.science.ubc.ca/pub/interface/ws_server.php")
        .build();
    LabsApi labsApi = api.create(LabsApi.class);
```

Each call to the generated implementation of your interface makes a request and parse
the response to the desired type

```java
    Lab lab = labsApi.getLab(1);
```

# License

    Copyright 2015 Manuel Vera Nieto

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
