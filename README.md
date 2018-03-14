# Spring Boot "Inventory Management System(IMS)"

This is a Java POC for Inventory management for a multi-outlets restaurant chain.

Features: 
- User authentication 
- Product management 
- Ingredient management

 Steps to kick start the application:
This application is packaged as a jar which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary.

1. Import the project as existing gradle project.
2. Make sure you are using JDK 1.8 and Gradle
2. Run application with the  following configuration.
```
    "java -jar IMS-1.0.jar --spring.config.location=config/DEV/ --spring.profiles.active=DEV"
```
3. Check the stdout or IMS.log file to make sure no exceptions are thrown. Once the application runs you should see something like this

```
2017-11-20 15:16:22.710 INFO  o.s.b.c.e.t.TomcatEmbeddedServletContainer  - Tomcat started on port(s): 8080 (http)
2017-11-20 15:16:22.710 DEBUG o.s.w.c.s.StandardServletEnvironment  - Adding [server.ports] PropertySource with highest search precedence
2017-11-20 15:16:22.716 INFO  c.b.i.IMSFrameworkApplication  - Started IMSFrameworkApplication in 32.385 seconds (JVM running for 32.783)
```
4. Application starts on default 8080 port. Browse the following URL to view the basic swagger documentation.
```
 http://localhost:8080/swagger-ui.html
```

## About the Service

The service is just a Inventory management system REST service. It uses an mysql hosted on AWS to store the data. 

Here is what this little application demonstrates: 

* Authenticates the end user is done by calling /ims/login and provides JWT token (Access token)
* Access token needs to be provided in header for accessing all other IMS API.
* After successful login, end user needs to perform the following using exposed rest API's
    * Create Categories. 
    	* Creates a specific category such as Starter, MainCourse, Supper, Desert, Beverages, etc..
    	* User can add/update/disable/get category using the provided REST API's.
    * Create Ingredients with available quantity.
    	* Creates an ingredient with a specified quantity, this quantity is the stock available in the kitchen. (milk, chocolate, sugar, bread, cream, etc..)
    	* User can add/update/delete/get ingredients using the provided REST API's.
    * Create Product for a category and provide ingredients required.
    	* End user needs to specify the ingredient for the product. It is assumed here that, 1 unit of ingredient is needed for every order placed. (Product - Milkshake with ingredients - sugar, milk, cream, chocolate)
    	* User can add/update/get product using the provided REST API's.
    * Order a product by Category id and product id.
    	* While placing an order system checks the ingredient quantity available in the store and the places or rejects the order.
    	 
* All APIs are "self-documented" by Swagger2 using annotations 

##Assumptions
Since the Application aims at serving multiple outlets, hence I have created a meta table for outlets and users.

Outlet -1 based at Indonesia
Outlet -2 based at Malaysia 
Outlet -3 based at Singapore

And John works for outlet -1, scot at outlet -2 and Adam at outlet -3. Their credentials are mentioned below.

username - john@doe.com 
password -123456

username - scot@yahoo.com
password - tiger

username - adam@outlook.com
password - 123456


Here are some endpoints you can call:

Login API:
```
POST /ims/login HTTP/1.1
Host: localhost:8080
Authorization: Basic c2NvdEB5YWhvby5jb206dGlnZXI=
uuid: 2323
```
Create Category:
```
POST /ims/category HTTP/1.1
Host: localhost:8080
AccessToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20iLCJpYXQiOjE1MTExMjQ4NTgsInN1YiI6IlNjb3QiLCJhdWQiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20vYWNjZXNzIiwib3V0bGV0IjoyfQ.9PWppohCOsoYPHV963WygMkan2G4r9zNIHPdrFYLRw0
uuid: 2232312
Content-Type: application/json
{
	"name":"Starter",
	"description":"Yummy Starter",
	"enabled":"Y"
}
```
Get All Categories for the logged in used Outlet.
```
GET /ims/category/ HTTP/1.1
Host: localhost:8080
AccessToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20iLCJpYXQiOjE1MTExMjQ4NTgsInN1YiI6IlNjb3QiLCJhdWQiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20vYWNjZXNzIiwib3V0bGV0IjoyfQ.9PWppohCOsoYPHV963WygMkan2G4r9zNIHPdrFYLRw0
uuid: 22323
Content-Type: application/json
```

Get particular category by id.
```
GET /ims/category/1 HTTP/1.1
Host: localhost:8080
AccessToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20iLCJpYXQiOjE1MTExMjQ4NTgsInN1YiI6IlNjb3QiLCJhdWQiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20vYWNjZXNzIiwib3V0bGV0IjoyfQ.9PWppohCOsoYPHV963WygMkan2G4r9zNIHPdrFYLRw0
uuid: 22323
Content-Type: application/json
```
Update Category by id 
```
PUT /ims/category/13 HTTP/1.1
Host: localhost:8080
AccessToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20iLCJpYXQiOjE1MTExMjM1NTAsInN1YiI6IkpvaG4iLCJhdWQiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20vYWNjZXNzIiwib3V0bGV0IjoxfQ.vdqwSJbwRY0GM0ypmMdqmFIYNKpXlsojbc7Clahb4fM
uuid: 22323
Content-Type: application/json
{
	"name":"Starter 6",
	"description":"delicious Starters By Our Chef",
	"enabled":"Y"
}
```
Delete/Disable category by Id 
```
DELETE /ims/category/13 HTTP/1.1
Host: localhost:8080
AccessToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20iLCJpYXQiOjE1MTExMjM1NTAsInN1YiI6IkpvaG4iLCJhdWQiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20vYWNjZXNzIiwib3V0bGV0IjoxfQ.vdqwSJbwRY0GM0ypmMdqmFIYNKpXlsojbc7Clahb4fM
uuid: 22323
```

Create Ingrediants :
```
POST /ims/ingredient/ HTTP/1.1
Host: localhost:8080
AccessToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20iLCJpYXQiOjE1MTExMjQ4NTgsInN1YiI6IlNjb3QiLCJhdWQiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20vYWNjZXNzIiwib3V0bGV0IjoyfQ.9PWppohCOsoYPHV963WygMkan2G4r9zNIHPdrFYLRw0
uuid: 22323
Content-Type: application/json
{
	"name": "Cream",
    "availQty": 10
 
}
```
Get All Ingredients for the logged in user outlet.
```
GET /ims/ingredient/ HTTP/1.1
Host: localhost:8080
AccessToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20iLCJpYXQiOjE1MTExMjQ4NTgsInN1YiI6IlNjb3QiLCJhdWQiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20vYWNjZXNzIiwib3V0bGV0IjoyfQ.9PWppohCOsoYPHV963WygMkan2G4r9zNIHPdrFYLRw0
uuid: 22323
Content-Type: application/json
```
Get  Ingredients By id
```
GET /ims/ingredient/1 HTTP/1.1
Host: localhost:8080
AccessToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20iLCJpYXQiOjE1MTExMjQ4NTgsInN1YiI6IlNjb3QiLCJhdWQiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20vYWNjZXNzIiwib3V0bGV0IjoyfQ.9PWppohCOsoYPHV963WygMkan2G4r9zNIHPdrFYLRw0
uuid: 22323
Content-Type: application/json
```
Delete Ingredient by id.
```
DELETE /ims/ingredient/9 HTTP/1.1
Host: localhost:8080
AccessToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20iLCJpYXQiOjE1MTExMjQ4NTgsInN1YiI6IlNjb3QiLCJhdWQiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20vYWNjZXNzIiwib3V0bGV0IjoyfQ.9PWppohCOsoYPHV963WygMkan2G4r9zNIHPdrFYLRw0
uuid: 22323
```
Update Ingredients by id.
```
PUT /ims/ingredient/4 HTTP/1.1
Host: localhost:8080
AccessToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20iLCJpYXQiOjE1MTEwMTM5NjQsInN1YiI6IlJvb3QiLCJhdWQiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20vYWNjZXNzIiwib3V0bGV0IjoyfQ.QwzPFtRt6VsUwNxKCDYRp_pSHAEu4xhNSQJhUw_SuLc
uuid: 22323
Content-Type: application/json
{
    "availQty": 10
}
```
Create product for a category.
```
POST /ims/category/20/product/ HTTP/1.1
Host: localhost:8080
AccessToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20iLCJpYXQiOjE1MTExMjQ4NTgsInN1YiI6IlNjb3QiLCJhdWQiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20vYWNjZXNzIiwib3V0bGV0IjoyfQ.9PWppohCOsoYPHV963WygMkan2G4r9zNIHPdrFYLRw0
uuid: 22323
Content-Type: application/json
{
	"name":"MilkShake",
	"description":"Sweet and cold",
	"price":200,
	"ingredents":[8,11,16,17]
	
}
```
Get all Products by category Id
```
GET /ims/category/20/product HTTP/1.1
Host: localhost:8080
AccessToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20iLCJpYXQiOjE1MTExMjQ4NTgsInN1YiI6IlNjb3QiLCJhdWQiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20vYWNjZXNzIiwib3V0bGV0IjoyfQ.9PWppohCOsoYPHV963WygMkan2G4r9zNIHPdrFYLRw0
uuid: 22323
Content-Type: application/json

```
Get  Product by category Id and product Id.
```
GET /ims/category/20/product HTTP/1.1
Host: localhost:8080
AccessToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20iLCJpYXQiOjE1MTExMjQ4NTgsInN1YiI6IlNjb3QiLCJhdWQiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20vYWNjZXNzIiwib3V0bGV0IjoyfQ.9PWppohCOsoYPHV963WygMkan2G4r9zNIHPdrFYLRw0
uuid: 22323
Content-Type: application/json
```
Update Product by category id and product id.
```
PUT /ims/category/9/product/29 HTTP/1.1
Host: localhost:8080
AccessToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20iLCJpYXQiOjE1MTExMTYyNTcsInN1YiI6IkpvaG4iLCJhdWQiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20vYWNjZXNzIiwib3V0bGV0IjoxfQ.JTlUu5GtdMfUW2P3WwZf0adgVcw_m0Xj3dQpD1qnwME
uuid: 22323
Content-Type: application/json

{
    "ingredents": [2]
}
```

Place order specifying quantity.
```
PUT /ims/category/20/product/9/order?qty=10 HTTP/1.1
Host: localhost:8080
AccessToken: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20iLCJpYXQiOjE1MTExMjQ4NTgsInN1YiI6IlNjb3QiLCJhdWQiOiJodHRwczovL2JpcmFqLmludmVudG9yeS5jb20vYWNjZXNzIiwib3V0bGV0IjoyfQ.9PWppohCOsoYPHV963WygMkan2G4r9zNIHPdrFYLRw0
uuid: 22323
```