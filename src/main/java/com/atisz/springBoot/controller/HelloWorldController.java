package com.atisz.springBoot.controller;

import com.atisz.springBoot.domain.User;
import com.atisz.springBoot.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RestController
public class HelloWorldController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/hello")
    public String index() {
        return "hello world";
    }

    @RequestMapping("/getUser")
    public User getUser() {
        User user = new User();
        user.setName("aaa");
        user.setPassword("123");
        return user;
    }

    /**
     * 分页查询
     * @return
     */
    @RequestMapping("/getAllUserByPage")
    public Page<User> getAllUserByPage() {
        int page = 0;
        int size = 10;
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        Page<User> userPage = userRepository.findAll(pageable);

        List<User> userList = userPage.getContent();
        System.out.println("Content:");
        for (User user : userList) {
            System.out.println(user.toString());
        }

        return userPage;
    }

    @RequestMapping("/getAllUser")
    public List<User> getAllUser() {

        List<User> userList = userRepository.findAll();
        return userList;
    }

    @RequestMapping("/addUser")
    public User addUser() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userRepository.save(new User("aa1", "aa123456","aa@126.com", "aa", formattedDate));
        userRepository.save(new User("bb2", "bb123456","bb@126.com", "bb", formattedDate));
        userRepository.save(new User("cc3", "cc123456","cc@126.com", "cc", formattedDate));

        List<User> userList = userRepository.findByEmailLike("aa@126.com");
        System.out.println(userList.size());
        for (User user : userList) {
            System.out.println(user.toString());
        }

        return userRepository.findByNameOrEmail("aa","cc@126.com");
    }
}
