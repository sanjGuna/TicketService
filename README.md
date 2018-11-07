# Ticket Service
---
This project is an implementation of a simple ticket service that facilitates the discovery, temporary hold, and final reservation of seat within a high-demand performance venue.

This is a simple java console-based application that uses Gradle and Java 8.


### Assumptions
---
1. There can be more than one venue available in a complex.
2. Find available slot will give all the seats that are not hold or confirmed.
3. System have basic data loaded for venue and seating data.
4. A row can have variable number of seats and a venue complex has a unique number for each seat.
5. Users are not allowed to see historical availability.
6. A user must be a valid user in the system.
7. Expiration will not happen until someone actually look for available seats. If a user does not confirm with set number of secs it will expire.
8. User will be presented all available seating with their star rating. Then use will select the seats they want by entering seat numbers.
9. Only one show per day and seat can be available for other days if it is reserved for one day.


### Executing the Console Application
---
1. Please make sure you have git installed and JAVA_HOME setup
2. Clone TicketService Application
    ```
    git clone https://github.com/sanjGuna/TicketService.git
    ```

3. Run the following command to get the Jar build locally using a git bash or similar
   ```
   cd TicketService
   
   ./gradlew build
   ```
   
4. Testing 
   ```
    ./gradlew test
    ```
   
5. You will find TicketService-1.0-SNAPSHOT.jar inside build/libs folder and please execute following comand in a windows command prompt or mac terminal as this application uses console as a UI.
   ```
   cd build/libs
   Java -jar TicketService-1.0-SNAPSHOT.jar
   ```
   
 5.You will be prasented with following Screen.
 ![consoleApp](https://github.com/sanjGuna/TicketService/blob/master/ConsoleApplication.png)
 
 
### Using Application
   
Select Following options.
```
A. Find Available Seats
B. Hold seat for a client
C. Confirm Booking
D. Exit
```

####Select Option A:

You will be prompted to enter the date that you would like to get availability. 
![Availability](https://github.com/sanjGuna/TicketService/blob/master/Availability.png)

Now you can hold seats based on your preference. You will need to enter userid. (Try testuser), date(01/01/2019), comma separated seat ids. Once it is on hold you will get a confirmation number.

![optionB](https://github.com/sanjGuna/TicketService/blob/master/optionB.png)

Now you will have the conformation number (Above screen gave 1), use OPTION C and use conformation number to confirm your seat.

![optionC](https://github.com/sanjGuna/TicketService/blob/master/optionC.png)

Now if you run the Option A get available seats on same days as above 01/01/2019. You will see selected seat numbers from row one is not available. (only 6,9 10 available)

![optionA](https://github.com/sanjGuna/TicketService/blob/master/OptionA.png)


    
### Testing, Code Coverage and static code analysis results.
AAs you can see in the following screen all test is passed and no sonar lint issues. Also, I have a high code coverage and there were no tests added for UI classes as they only used to demonstrate the work. 
![Analysis](https://github.com/sanjGuna/TicketService/blob/master/Analysis.png)

    
