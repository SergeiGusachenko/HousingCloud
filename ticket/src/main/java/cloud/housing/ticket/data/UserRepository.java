package cloud.housing.ticket.data;

import cloud.housing.ticket.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u from User u Where u.username = :username")
    public User getUserByUsername(@Param("username") String username);

}
