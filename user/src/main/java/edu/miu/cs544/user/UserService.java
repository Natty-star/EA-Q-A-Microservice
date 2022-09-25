package edu.miu.cs544.user;

import edu.miu.cs544.user.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<AppUser> getUsers(){
        return userDao.findAll();
    }
    public AppUser findByUserName(String name){
        return userDao.findByUserName(name);
    }
    public AppUser getUser(Long id){
        return  userDao.findById(id).get();
    }
    public void createUser(AppUser appUser){
        userDao.save(appUser);
    }
    public void deleteUser(Long id){
        AppUser appUser = userDao.findById(id).get();
        Boolean active = appUser.getActive();
        appUser.setActive(!active);
        userDao.save(appUser);
    }
    public void editUser(Long id,AppUser appUser){
        userDao.save(appUser);
    }
}
