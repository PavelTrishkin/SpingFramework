package ru.gb.trishkin.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.trishkin.spring.mvc.domain.Product;
import ru.gb.trishkin.spring.mvc.domain.Users;
import ru.gb.trishkin.spring.mvc.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getList(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users-list";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable Long id) {
        Users byId = userService.getById(id);
        model.addAttribute("user", byId == null ? new Users() : byId);
        return "user";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        System.out.println(userService.getById(id).getName());
        model.addAttribute("user", userService.getById(id));
        return "edit-users";
    }

    @PostMapping("/edit")
    public String modifyProduct(Users user) {
        userService.createOrSaveUser(user);
        return "redirect:/users/";
    }

    @PostMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

}
