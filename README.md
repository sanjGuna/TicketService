# Ticket Service
---
This project is an implemenation of a simple ticket service that facilitates the discovery, temporary hold, and final reservation of seat within a highp-demand performence venue.

This is a simple java console based application that uses Gradle and Java 8.

### Assumptions
---
1. There can be more than one venue available in a complex.
2. Find avaialable slot will give all the seats that are not hold or confirmed.
3. System have basic data loaded for veenue and seating data. 
4. A row can have variable number of seates and a venue complex has a unique number for each seat.
5. Users are not allowed to see historical availability.
6. A user must be a valid user in the system.
7. Expiaration will not happen untill someone actually look for avaiabkle seats.If a user does not confirm with set number of secs it will expire.
8. User will be prasented all avaiable seating with there star rating. Then use will select the seats they want by entering seat numbers.
9. Only one show perday and seat can be available for other days if it is reserved for one day.

### Running the Console Application
---
1. Please make sure you have git intalled and JAVA_HOME setup
2. Clone TicketService Application
    git clone https://github.com/sanjGuna/TicketService.git

3. Run the following command to get the Jar build locally
   cd TicketService
   
   ./gradlew build
   
   cd build/libs
   
 4. You will find TicketService-1.0-SNAPSHOT.jar inside libs folder and lets execute that.
   Java -jar TicketService-1.0-SNAPSHOT.jar
   
 5.You will be prasented with following Screen.
 ![consoleApp](https://github.com/sanjGuna/TicketService/blob/master/ConsoleApplication.png)
      
    
