#SEP 2015 - Acquirer REST service

REST service for PaymentApp

Spring Boot & MongoDB

##Requirements

 * JDK 8
 * Apache Maven
	   
   
##Important methods:

* generatePaymentInstructions

	https://localhost:8082/api/payment/paying
	
	HTTP: POST
	
		 {
		 	 "merchantId": "merchantId",
		 	 "merchatPassword": "merchantPassword",
		 	 "amount": "500",
		          "merchantInfo": {
		              "merchantOrderId": 1,
		              "merchantTimestamp": "25.01.2016 17:36:33"
		          },
		       
		         "errorUrl": "www.example.com"
		  }  
		 
		
* payInsurance

	https://localhost:8082/api/payment/paying/{paymentID}
	
	HTTP: POST
	
		 {
		 	 "pan": "1111111111111111",
		 	 "securityCode": "111",
		 	 "holderName": "Card 1",
		 	 "expirationDate": "11/17"
		        
		  }  
		 


    
    
* getAmountToPay

	https://localhost:8082/api/payment/paying/{paymentID}
	
	HTTP: GET


        
         
         {
              "paymentID" : 5
         }

##How to start project

- clone development branch from github
	git clone -b development https://github.com/LaudaDev/sep-merchant-web-app.git
	
- in eclipse import project as existing maven project

- right click on project -> team -> disconnect

- right click on src folder -> build path -> use as source folder

- right click on project -> run as -> maven build and set goals on "clean install"

- run as -> java application (select application and click ok)

- in case of Error: Could not find or load main class app.Application error, go to project -> clean and run again


##### Run mongodb script (initDb.js from dbIniti) once to insert initial data.
Run project with PaymentApp and Merchant backend and frontend apps (https://github.com/LaudaDev/sep-merchant-web-app.git) to test all avaliable methods.
