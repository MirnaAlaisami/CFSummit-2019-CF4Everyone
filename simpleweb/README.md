---------------------------------------  

cf login -a \<APIEndpint>  
cf push simpleweb -p target\simpleweb-0.1-SNAPSHOT.jar -m 750M --random-route  
cf apps  
  
---------------------------------------  

cf marketplace  
cf create-service \<SERVICE> \<PLAN> simpleweb-db  
cf services  
  
---------------------------------------  

cf bind-service first-push-app simpleweb-db  
cf restage simpleweb 
  
---------------------------------------  

cf scale simpleweb -i 2  
cf apps  
