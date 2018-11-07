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

### Executing the Console Application
---
1. Please make sure you have git intalled and JAVA_HOME setup
2. Clone TicketService Application
    ```
    git clone https://github.com/sanjGuna/TicketService.git
    ```

3. Run the following command to get the Jar build locally using a git bash 
   ```
   cd TicketService
   
   ./gradlew build
   ```
   
4. Testing 
   ```
    ./gradlew test
    ```
   
5. You will find TicketService-1.0-SNAPSHOT.jar inside build/libslibs folder and lets execute that.
   cd build/libslibs
   ```
   Java -jar TicketService-1.0-SNAPSHOT.jar
   ```
   
 5.You will be prasented with following Screen.
 ![consoleApp](https://github.com/sanjGuna/TicketService/blob/master/ConsoleApplication.png)
 
 
### Using Application
   
Select Following options . 
A. Find Available Seats
B. Hold seat for a client
C. Confirm Booking
D. Exit

Option A:

You will be promted to enter the date  that you would like to get availability.
![Availability](https://github.com/sanjGuna/TicketService/blob/master/Availability.png)

Now you can hold seats ids based on your prefernce.
You will need to enter userid. (Tyr testuser), date(01/01/2019), comma seperated seatd ids. One it is on hold you will get a confirmation number.

![optionB](https://github.com/sanjGuna/TicketService/blob/master/optionB.png)

    
    
    
