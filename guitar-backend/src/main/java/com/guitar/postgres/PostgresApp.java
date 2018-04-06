package com.guitar.postgres;

import com.guitar.model.User;
import com.guitar.service.UserService;
import com.guitar.web.CustomException;
import com.guitar.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostgresApp {

    UserService userService;

    @Autowired
    public PostgresApp(UserService userService) {
        this.userService = userService;
    }

    public void go() {




        User u1 = User.builder().name("Piotr").surname("Boski").nick("slovik").email("piotr@gmail.com").password("password").level("1").build();
        System.out.println(u1.isAccountNonLocked());
        User u2 = User.builder().name("Michal").surname("Kowalski").nick("misiek").email("michal@gmail.com").password("234567").level("1").build();
        User u3 = User.builder().name("Artek").surname("Nowak").nick("aris").email("artek@gmail.com").password("345678").level("2").build();
        User u4 = User.builder().name("Anna").surname("Wanna").nick("keri").email("anna@gmail.com").password("456789").level("3").build();

      //  User u5 = User.builder().name("Michal").surname("Kowalski").nick("misiek").email("misiura@gmail.com").password("abcdefg").build();

        try {
            userService.createUser(userService.userToDto(u1));
        } catch (CustomException e) {
            e.printStackTrace();
        }
        try {
            userService.createUser(userService.userToDto(u2));
        } catch (CustomException e) {
            e.printStackTrace();
        }
        try {
            userService.createUser(userService.userToDto(u3));
        } catch (CustomException e) {
            e.printStackTrace();
        }
        try {
            userService.createUser(userService.userToDto(u4));
        } catch (CustomException e) {
            e.printStackTrace();
        }

        //   userService.updateUser(userService.userToDto(u5));

        System.out.println(userService.getAllUsers());



        System.out.println("AAAAA");
        if(userService==null)
            System.out.println("no nie");
        //userService.createUser();
        System.out.println("BBBBBB");
        UserDTO user = null;
        try {
            user = userService.getUserWithNick("misiek");
        } catch (CustomException e) {
            e.printStackTrace();
        }
        // if(user.isPresent()){
        System.out.println(user);
        // }
        System.out.println("CCCCCC");



    /*

        // For Annotation
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation =
                (MongoOperations) ctx.getBean("mongoTemplate");

        // Case1 - insert a user, put "tableA" as collection name
        System.out.println("Case 1...");
        User userA = new User("1000", "apple", 54, new Date());
        mongoOperation.save(userA, "tableA");

        // find
        Query findUserQuery = new Query();
        findUserQuery.addCriteria(Criteria.where("ic").is("1000"));
        User userA1 = mongoOperation.findOne(findUserQuery, User.class, "tableA");
        System.out.println(userA1);

        // Case2 - insert a user, put entity as collection name
        System.out.println("Case 2...");
        User userB = new User("2000", "orange", 64, new Date());
        mongoOperation.save(userB);

        // find
        User userB1 = mongoOperation.findOne(
                new Query(Criteria.where("age").is(64)), User.class);
        System.out.println(userB1);

        // Case3 - insert a list of users
        System.out.println("Case 3...");
        User userC = new User("3000", "metallica", 34, new Date());
        User userD = new User("4000", "metallica", 34, new Date());
        User userE = new User("5000", "metallica", 34, new Date());
        List<User> userList = new ArrayList<User>();
        userList.add(userC);
        userList.add(userD);
        userList.add(userE);
        mongoOperation.insert(userList, User.class);

        // find
        List<User> users = mongoOperation.find(
                new Query(Criteria.where("name").is("metallica")),
                User.class);

        for (User temp : users) {
            System.out.println(temp);
        }

        //save vs insert
        System.out.println("Case 4...");
        User userD1 = mongoOperation.findOne(
                new Query(Criteria.where("age").is(64)), User.class);
        userD1.setName("new name");
        userD1.setAge(100);

        //E11000 duplicate key error index, _id existed
        //mongoOperation.insert(userD1);
        mongoOperation.save(userD1);
        User userE1 = mongoOperation.findOne(
                new Query(Criteria.where("age").is(100)), User.class);
        System.out.println(userE1);
    */
    }
}