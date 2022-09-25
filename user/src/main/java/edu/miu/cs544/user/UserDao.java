package edu.miu.cs544.user;

import edu.miu.cs544.user.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<AppUser,Long> {
    AppUser findByUserName(String name);
}
