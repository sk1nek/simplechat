package me.mjaroszewicz.user;


import me.mjaroszewicz.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepo")
public interface UserRepository extends CrudRepository<User, Long> {

    User findOneByName(String name);


}
