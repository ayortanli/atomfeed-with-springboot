# atomfeed-with-springboot
Demonstrates how atom feeds can be used for microservice communication

When developing a system with microservice architecture, one key challenge will be the communication with other services. Http based atom feeds are one of the solution for inter-service communication without any infrastructure requirement like event bus or message broker. 

"Web feeds allow software programs to check for updates published on a website. To provide a web feed, the site owner may use specialized software (such as a content management system) that publishes a list (or "feed") of recent articles or content in a standardized, machine-readable format. The feed can then be downloaded by programs that use it, like websites that syndicate content from the feed, or by feed reader programs that allow Internet users to subscribe to feeds and view their content." ([from wikipedia](https://en.wikipedia.org/wiki/Atom_(Web_standard)))

Microservices mostly require two types of communication. First one is retrieving data that is necessary for our own business logic but is created and maintained by another service (eg. catalog data, references etc.). This problem can easily be solved by atom feeds just by polling changes in those date and than by updating our own related domain objects. Second communication requirement is about carrying on a business process that is started in another one and will continue in our service. In that case, service should retrieve some data and start the next step of business transaction in its own domain. Atom feeds can also used for solving this type of communication problems. In order to do that mechanisms like [event sourcing](https://martinfowler.com/eaaDev/EventSourcing.html) can be used where source service stores domain events and publish them as feed to the clients. Then client should just subscribe to the feed and process its own business transactions for each new update.

I have used atom feed library called **"Rome"** with Spring-Boot. For simplicity, I have implemented both services in only one project which will act as both server and client in the communication logic. First service has been implemented under author package, and second one is under book package.

In our scenario, first service (author) is responsible for managing authors info. Second service (book) is responsible for managing books written by authors. With atom feeds, our second service retrieves authors changed data from first one in every 10 seconds and save/update them to its own domain. 

You can test application with rest calls like belows:

Service 1: (Author Service)
- curl -X GET http://localhost:8080/author/             --> lists all authors
- curl -X GET http://localhost:8080/author/{id}             --> lists author with id
- curl -X POST http://localhost:8080/author/ \\\
    -d '{ "name": "Stephen", "surname": "King", "country": "USA"  }'            --> inserts a new author
- curl -X PUT http://localhost:8080/author/4 \\\
    -d '{"name": "1. Stephen", "surname": "King", "country": "USA" }'           --> updates author with id
- curl -X DELETE http://localhost:8080/author/4             --> deletes author with id
- http://localhost:8080/author/feed         --> returns author rss feed with update times

Service 2: (Book Service) (Updates in author service is replicated to book service in each 10 seconds)
- curl -X GET http://localhost:8080/book/             --> lists all books
- curl -X GET http://localhost:8080/book/{id}             --> lists book with id
- curl -X POST http://localhost:8080/book/ \\\
    -d '{"name": "Murtaza","publishYear": 1952,"author": {"id": 3,"name": "Orhan","surname": "Kemal","country": "Turkey"}}'         --> inserts a new book
- curl -X PUT http://localhost:8080/book/5 \\\
        -d '{"name": "Murtaza","publishYear": 1955,"author": {"id": 2}}'      --> updates book with id
- curl -X DELETE http://localhost:8080/book/5             --> deletes book with id


(To access database use http://localhost:8080/h2-console/  with jdbc url --> jdbc:h2:mem:testdb )