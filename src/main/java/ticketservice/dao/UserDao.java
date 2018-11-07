package ticketservice.dao;

import ticketservice.entity.User;
import ticketservice.mockdb.TicketServiceDB;

public class UserDao {
    public User find(String userId) {
        return TicketServiceDB.getUser(userId);
    }

    public String save(User user) {
        return TicketServiceDB.save(user);
    }
}
