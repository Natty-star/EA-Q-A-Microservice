package edu.miu.cs544.user;

import edu.miu.cs544.user.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<AppUser> getUsers(){
        //System.out.println(userService.getUsers());
        return userService.getUsers();

    }

    @GetMapping("/login/{name}")
    public AppUser findUser(@PathVariable String name){
        return userService.findByUserName(name);
    }

    @GetMapping("/{id}")
    public AppUser getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PostMapping("/register")
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser appUser){
        userService.createUser(appUser);
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("Successfully Deleted" , HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editUser(@RequestBody AppUser appUser, Long id){
        userService.editUser(id,appUser);
        return new ResponseEntity<>("Successfully Updated", HttpStatus.OK);
    }

}
