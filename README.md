### Exam preparation REST API
___

_Elaborate on some of the characteristics of REST, like: Stateless, Cacheable, Layered System, Uniform Interface etc._

######Stateless
REST is stateless, meaning that no state is maintained by the server between requests. The burden of 
maintaining state falls on the client application instead.

######Cachable
Cachable refers to the ability to cache responses made to a server. REST APIs are often more cachable
than traditional websites, because only pure data is sent to the client. 

An example of this could be a website where an administrator can view a list of users. The username of the authenticated 
administrator is displayed on the page.

Using a traditional web-application requests to the page cannot be cached to multiple administrators since the username
of the administrator is displayed on the page.

Using a single page application with a REST API, all calls to the endpoint that provides the users, can be cached. And
the username of the authenticated administrator can be displayed by the client application.

####Layered System

Layered systems are systems where concerns are separated into individual layers. REST APIs can be deployed to a server, 
and multiple client applications can use the same REST API. This separates the concerns of retrieving and displaying 
data into different layers.
 
#####Uniform Interface

Describes the standards that allow operations on resources in REST. The uniform interface constraint defines the 
interface between clients and servers. It simplifies and decouples the architecture, which enables each part to evolve 
independently. 

Individual resources are identified in requests using URIs as resource identifiers. The resources themselves are 
conceptually separate from the representations that are returned to the client. For example, the server does not send 
its database, but rather, some HTML, XML or JSON that represents some database records.

#####Client-Server

The client-server architecture is when a client sends requests to servers, and servers respond to requests made by clients.

In a single page website, the javascript running in the browser is the client, and the REST API is the server. Multiple 
clients can communicate with the same server (REST API).

___

_Explain the benefit(s) from having a backend that exposes all data to clients via a REST-API._

Some of the benefits are mentioned in the above chapter about REST APIs being cachable. The other benefits mostly arise 
because REST APIs create layered and well-separated systems. Because of this
many different clients can use and communicate with the same REST API.

REST APIs can easy development of applications since frontend and backend work is clearly separated.

___

_Elaborate on how JSON or XML supports communication between subsystems, even when the subsystems are implemented on different platforms._

JSON or XML supports communication between subsystems, even when the subsystems are implemented on different platform, because 
these formats are standardized. Because XML and JSON are defined standards, all servers, clients and libraries know how 
XML or JSON are formatted.

To use XML or JSON on a _new_ platform, all you need is a library that implements the specification of the data transfer language.