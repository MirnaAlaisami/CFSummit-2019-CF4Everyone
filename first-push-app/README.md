---------------------------------------  

cf login -a <APIEndpint>  
cf push first-push -p target\first-push-app-0.1-SNAPSHOT.jar --random-route  
cf apps  
  
---------------------------------------  

cf marketplace  
cf create-service <SERVICE> <PLAN> first-push-db  
cf services  
  
---------------------------------------  

cf bind-service first-push-app first-push-db  
cf restage first-push-app  
  
---------------------------------------  

cf scale first-push -i 2  
cf apps  
