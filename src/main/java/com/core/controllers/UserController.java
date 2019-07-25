package com.core.controllers;

import com.core.models.*;
import com.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user")
public class UserController extends AbstractController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BasicModel> register(@RequestBody UserModel userModel) throws Exception {
        return handle(userService.register(userModel));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BasicModel> update(@RequestBody UserModel userModel) throws Exception {
        return handle(userService.update(userModel));
    }

    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserModel> getUser(@PathVariable("id") long id) throws Exception {
        return handle(userService.getUser(id));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UserModel> register(@RequestBody LoginModel loginModel) throws Exception {
        return handle(userService.login(loginModel));
    }

//    @PreAuthorize("@UserService.isOwnerUser()")
@RequestMapping(value = "/listInActiveUsers", method = RequestMethod.GET)
@ResponseBody
public ResponseEntity<UserListModel> listInActiveUsers() {
    return handle(userService.listInActiveUsers());
}


//    @RequestMapping(value = "/listAllUsers", method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseEntity<UserListModel> listAllUsers() {
//        return handle(userService.listAllUsers());
//    }
//

    @RequestMapping(value = "/listDelegateUsers", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserListModel> listDelegateUsers() {
        return handle(userService.listDelegateUsers());
    }


    @RequestMapping(value = "/listStoresUsers", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserListModel> listStoresUsers() {
        return handle(userService.listStoresUsers());
    }

    //    @PreAuthorize("@UserService.isOwnerUser()")
    @RequestMapping(value = "/activateBlockUser/{id}/{isActivate}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BasicModel> activateUser(@PathVariable("id") long id, @PathVariable("isActivate") boolean isActivate) {
        return handle(userService.activateUser(id, isActivate));
    }

    @RequestMapping(value = "/changeLanguage/{id}/{lang}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BasicModel> changeLanguage(@PathVariable("id") long id, @PathVariable("lang") String lang) {
        return handle(userService.changeLanguage(lang, id));
    }

}
