# atomfeed-with-springboot
Demonstrates how atom feeds can be used for inter-service communication on spring boot

When developing a system with microservice architecture, one key challenge will be the communication with other services. Http based atom feeds are one of the widely used solution for inter-service communication without any infrastructure requirement. 

"Web feeds allow software programs to check for updates published on a website. To provide a web feed, the site owner may use specialized software (such as a content management system) that publishes a list (or "feed") of recent articles or content in a standardized, machine-readable format. The feed can then be downloaded by programs that use it, like websites that syndicate content from the feed, or by feed reader programs that allow Internet users to subscribe to feeds and view their content." ([from wikipedia](https://en.wikipedia.org/wiki/Atom_(Web_standard)))

Microservices require two types of communication. First one is retrieving data that is necessary for our own business logic but is created and maintained by another service (eg. catalog data, references etc.). This problem can easily be solved by atom feeds just by polling changes in those date and than by updating our own related domain objects. Second communication requirement is about carrying on a business process that is started in another one and will continue in our service. In that case, service should retrieve some data and start the next step of business transaction in its own domain. Atom feeds can also used for solving this type of communication problems. In order to do that mechanisms like [event sourcing](https://martinfowler.com/eaaDev/EventSourcing.html) can be used where source service stores domain events and publish them as feed to the clients. Then client should just subscribe to the feed and process its own business transactions for each new update.

Here I will show the usage of atom feeds in inter-service communication. I will use atom feed library called **"Rome"** with Spring-Boot. For simplicity, I will implement only one application which will act as both server and client in the communication logic. 

In our scenario, first service is responsible for managing authors info. Second service is responsible for managing books written by authors. With atom feeds, our second service retrieves authors changed data from first one in every 5 seconds and save them to its own domain. 

You can test application with rest calls like belows:

TBD  


ToDos
- Simple Spring Data JPA implementation with rest interface
- Rome atom feed integration (check over http)
- Duplicate projects as two different microservice (one as server and another as client)
- Communicate services with rome