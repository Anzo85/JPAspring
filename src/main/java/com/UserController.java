package com;


import com.DAO.UserDAO;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserDAO userDAO;


    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public String getAllUsers() {
        return "users";
    }

    //для того, чтбы достучаться к контролеру - достаточно ввести
    //<host>:<port>/<webcontext>/users ---> controller
    //для того, чтбы достучаться к методу getAllUsers - достаточно ввести
    //<host>:<port>/<webcontext>/users/list ---> метод
    @RequestMapping(method = RequestMethod.GET, value = "/hello/{login}/{password}")
    public String sayHello(Model model, @PathVariable(value = "login") String name,
                           @PathVariable(value = "password") String password) {
        model.addAttribute("user_name", String.format("JPA test for user with cred %s %s", name, password));
        return "users";//страничку для возврата
    }

    @RequestMapping(method = RequestMethod.GET, value = "/usersEntityList")
    public String getAllUsers(Model model) {
        model.addAttribute("usersList", userDAO.getUsers());
        return "users";//страничку для возврата
    }

}