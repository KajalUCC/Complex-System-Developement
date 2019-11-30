# Complex-System-Development

## Data Center Infrastructure and Traffic Management

A GUI tool for adding and managing the servers along with their metrics. The tools also allow you to monitor the traffic routing.

Introduction:
Data Centers are centralized locations where computing and networking equipment is put together for the purpose of collecting, storing and processing the data. They have various layers and come in various topologies and they have a huge resource consumption and need tuning for efficiency.

One such problem we have identified is traffic management. We have designed a solution and tool for traffic management in Data Centres. This has similar functionality to a DCIM.

We have provided a solution where the Controller acts as a load balancer and handles the incoming requests. All the Metrics like the number of servers available, CPU, IO, storage space and temperature are monitored in the tool and the request is routed accordingly.

## Tools & Technologies used :
MySQL 8.0 for Database.
HTML,CSS for UI.
Java EE 1.8 for Server Side Implementation.

### Setup Guide:-
1. Install MySQL database locally on the machine and connect to the database through MySQL workbench.
2. Clone the Git repository on the local machine and import the project in IntelliJ or Eclipse.
3. Add mysql-connector-java-8.0.18 jar as external jar to the project.
4. Create tables as per database schema(mentioned in the document) in MySQL database.
5. Change database connection url and credentials in the DBConnect file under src package.
6. Now the project can be executed successfully.




  
