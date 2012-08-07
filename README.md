Java and Android Clients for the Cloudera Manager API
===========================

Java and Android clients for the Cloudera Manager REST API (http://cloudera.github.com/cm_api/apidocs/v1/index.html)

(work in progress)

## Overview

The `cm-domain` project provides java representation of the Cloudera Manager Data Model (http://cloudera.github.com/cm_api/apidocs/v1/model.html)   

The `cm-service` project implements java services to repesent the Cloudera Manager REST API (http://cloudera.github.com/cm_api/apidocs/v1/rest.html).
All services uses the `HttpService` interface to abstract the HTTP communication. This allows both the `cm-domain` and 
the `cm-service` to be reused with differnt HTTP communication implementations and diferent platforms (like desktop or android).

The `cm-java-client` project provides `desktop` implementaton of the HTTP communication. The `JavaHttpService` implements 
`HttpService` using Spring's `RestTemplate` library (http://static.springsource.org/spring/docs/3.0.x/javadoc-api/org/springframework/web/client/RestTemplate.html)

The `cm-android-client` provides `android` compatible HTTP communication implementation.

## Java Client Example

    import java.util.List;
    import com.logaritex.hadoop.configuration.manager.domain.Host;
    import com.logaritex.hadoop.configuration.manager.service.ClouderaManager;

    public class JavaClientExample {

        public static void main(String[] args) {

      		ClouderaManager cm = ClouderaManagerFactory.createClouderaManager(
              "http://192.168.15.52:7180",
              "username", 
              "password");

      		List<Host> hosts = cm.getHostService().getAllHosts();

  	    	for (Host host:hosts) {
	  		    System.out.println(host);
  	    	}
    	}
    }

## Android Client Example

comming soon