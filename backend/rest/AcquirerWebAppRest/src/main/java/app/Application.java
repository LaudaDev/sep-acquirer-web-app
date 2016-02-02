package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

 public static void main(String[] args) throws Exception {
	 SpringApplication.run(new Object[] { Application.class }, args);
 }
	/* static {
		    //for localhost testing only
		    javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
		    new javax.net.ssl.HostnameVerifier(){

		        public boolean verify(String hostname,
		                javax.net.ssl.SSLSession sslSession) {
		            if (hostname.equals("localhost")) {
		                return true;
		            }
		            return false;
		        }
		    });
	 }
	 */
}