#phone book
pre-installations

###1.install monogodb and create database phone_book_db
    mongo/setup.sh
    
###2.install rabitmq and run Script rabbit/rabbit.groovy 

    set
    
        1.username 
        2.password  
        3.host in script
        
        
###3.set connection property in application.properties for mongodb

    spring.data.mongodb.host=
    spring.data.mongodb.username=
    spring.data.mongodb.database=
    spring.data.mongodb.password=
    
###4.set connection property in application.properties for rabbitMq

    spring.rabbitmq.host=
    spring.rabbitmq.username=
    spring.rabbitmq.password=
      
###technologies

    1.mongodb => for repository
    2.rabbitMq => for async get reponame from github
    3.feign => github api call
    4.swagger => document
    5.lombok
    6.mapstruct
    7.queryDsl => query for search


###api documentation in 

    http://localhost:6070/azmoon/swagger-ui.html 

#actions:

   ###1.add phone book 
   
        1.1 save to db
        1.2.use Aspect interceptor after insert phone book send to rabbitmq 
        1.3.listener for inquiry form github repository  and save to phone book
   ###2.search phone book 
   

#Approach
The messageQueue system is used to control the traffic load and by setting the status field for each record, by entering each record, it is saved and placed in query mode.
after save record to repository fire trigger a aspect and push record to rabbitmq for inquiry of github.(use @GitHub)
In the event of an error in the system during the query, a time service is executed for all records and returned to the query queue.
QueryDsl is used to search.

