package com.core.services;

import com.core.entites.Address;
import com.core.entites.User;
import com.core.models.*;
import com.core.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private StorageService storageService;

//    @Autowired
//    SecurityService securityService;

//    @Autowired
//    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    public static boolean isOwnerUser() {
//        UserModel userModel = getCurrentUserModel();
//        return userModel.getType().equals(Constants.USER_TYPE_OWNER);
//    }

//    public static UserModel getCurrentUserModel() {
//        return (UserModel) SecurityContextHolder.getContext().getAuthentication().getDetails();
//    }

//    public User getCurrentUser() {
//        return (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
//    }

    public UserListModel listInActiveUsers() {
        UserListModel userListModel = new UserListModel();
        List<User> users = userDao.findByActive(null);
        for(User user: users) {
            userListModel.getUserModels().add(constructUserModel(user));
        }
        return userListModel;
    }

    public UserModel constructUserModel(User user) {
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setEmail(user.getEmail());
        userModel.setLanguage(user.getLanguage());
        userModel.setUsername(user.getUsername());
        userModel.setMobile(user.getMobile());
        userModel.setType(user.getType());
        userModel.setName(user.getName());
        if(user.getAvatar() != null){
            userModel.setLogo(storageService.loadFile(user.getAvatar()).toString());
            userModel.setLogo(userModel.getLogo().substring(userModel.getLogo().indexOf("file"), userModel.getLogo().length() - 1));
        }
        userModel.setCity(user.getAddress().getCity());
        userModel.setGovernorate(user.getAddress().getGovernorate());
        userModel.setAddress(user.getAddress().getStreet());
        if("STORE".equals(user.getType())){
            userModel.setActive(user.getActive());
            userModel.setCommercialRegister(user.getCommercialRegister());
        }
        return userModel;
    }

    @Transactional
    public BasicModel register(UserModel userModel) {
        validateUser(userModel);
        User user = constructUser(userModel);
        userDao.save(user);
        return new BasicModel();
    }

    private void validateUser(UserModel userModel) {
        if (findByUsername(userModel.getUsername()) != null) {
            throw new RuntimeException("ERROR_USERNAME_ALREADY_EXISTING");
        }
        if (findByEmail(userModel.getEmail()) != null) {
            throw new RuntimeException("ERROR_EMAIL_ALREADY_EXISTING");
        }
    }

    public User constructUser(UserModel userModel) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setLanguage(userModel.getLanguage());
        user.setUsername(userModel.getUsername());
        user.setMobile(userModel.getMobile());
        user.setType(userModel.getType());
        user.setName(userModel.getName());
        user.setAvatar(userModel.getAvatar());
        user.setActive(userModel.getActive());
        user.setAddress(constructAddress(userModel));
        if(Constants.USER_TYPE_STORE.equals(userModel.getType())){
            user.setCommercialRegister(userModel.getCommercialRegister());
        }
        user.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
//        user.setRoles((Set<Role>) roleDao.findAll());
        return user;
    }

    public void constructUserForUpdate(UserModel userModel, User user) {
        user.setLanguage(userModel.getLanguage());
        user.setMobile(userModel.getMobile());
        user.setName(userModel.getName());
        user.setAvatar(userModel.getAvatar());
        user.setActive(userModel.getActive());
        constructAddress(userModel, user.getAddress());
        if(Constants.USER_TYPE_STORE.equals(userModel.getType())){
            user.setCommercialRegister(userModel.getCommercialRegister());
        }
    }

    private Address constructAddress(UserModel userModel) {
        Address address = new Address();
        address.setCity(userModel.getCity());
        address.setGovernorate(userModel.getGovernorate());
        address.setStreet(userModel.getAddress());
        return address;
    }

    private void constructAddress(UserModel userModel, Address address) {
        address.setCity(userModel.getCity());
        address.setGovernorate(userModel.getGovernorate());
        address.setStreet(userModel.getAddress());
    }

    private User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    private User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public BasicModel activateUser(long id, boolean isActivate) {
        User user = userDao.findFirstById( id);
        if(user == null){
            throw new RuntimeException("NOT_EXISTING_USER");
        }
        user.setActive(isActivate);
        userDao.save(user);
        return new BasicModel();
    }

    public User getUserById(long id) {
        return userDao.findFirstById(id);
    }

    public BasicModel changeLanguage(String lang, long id) {
        User user = userDao.findFirstById(id);
        user.setLanguage(lang);
        userDao.save(user);
        return new BasicModel();
    }

    public UserModel login(LoginModel loginModel) {
        User user = userDao.findByUsername(loginModel.getUsername());
        //TODO encrypt password
        if (user == null || !bCryptPasswordEncoder.matches(loginModel.getPassword(), user.getPassword())) {
            throw new RuntimeException("INVALID_USERNAME_OR_PASSWORD");
        }
        if(Constants.USER_TYPE_STORE.equals(user.getType()) && user.getActive() == null){
            throw new RuntimeException("NOT_APPROVED_YET");
        }
        if(Constants.USER_TYPE_STORE.equals(user.getType()) && !user.getActive()){
            throw new RuntimeException("ACCOUNT_REJECTED");
        }
        return constructUserModel(user);
    }

    public UserModel getUser(long id) {
        User user = userDao.findFirstById( id);
        if(user == null){
            throw new RuntimeException("NOT_EXISTING_USER");
        }
        return constructUserModel(user);
    }

    public UserListModel listDelegateUsers() {
        UserListModel userListModel = new UserListModel();
        List<User> users = userDao.findByActiveTrueAndType(Constants.USER_TYPE_DELEGATE);
        for(User user: users) {
            userListModel.getUserModels().add(constructUserModel(user));
        }
        return userListModel;
    }

    public UserListModel listStoresUsers() {
        UserListModel userListModel = new UserListModel();
        List<User> users = userDao.findByActiveTrueAndType(Constants.USER_TYPE_STORE);
        for(User user: users) {
            userListModel.getUserModels().add(constructUserModel(user));
        }
        return userListModel;
    }

    public BasicModel update(UserModel userModel) {
        User user = userDao.findFirstById(userModel.getId());
        if(user == null){
            throw new RuntimeException("NOT_EXISTING_USER");
        }
        constructUserForUpdate(userModel, user);
        userDao.save(user);
        return new BasicModel();
    }

//    public UserListModel listUsers(long userId) {
//        UserListModel userListModel = new UserListModel();
//        List<User> users = userDao.findByActive(true);
//        for(User user: users) {
//            userListModel.getUserModels().add(constructUserModel(user));
//        }
//        return userListModel;
//    }
}
