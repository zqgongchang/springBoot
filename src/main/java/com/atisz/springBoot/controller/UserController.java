package com.atisz.springBoot.controller;

import com.atisz.springBoot.domain.User;
import com.atisz.springBoot.domain.UserRepository;
import com.atisz.springBoot.entity.UserEntity;
import com.atisz.springBoot.enums.UserSexEnum;
import com.atisz.springBoot.mapper.UserMapper;
import com.atisz.springBoot.mapper.UserMapper_xml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.util.*;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserMapper_xml userMapper_xml;

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
     * jpa默认分页查询
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

    /**
     * jpa默认查询
     * @return
     */
    @RequestMapping("/getAllUser")
    public List<User> getAllUser() {

        List<User> userList = userRepository.findAll();
        return userList;
    }

    /**
     * 自定义简单查询
     * @return
     */
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

    /**
     * 与mybatis结合-注释方式
     * @return
     */
    @RequestMapping("/mybatis/getAllUser")
    public List<UserEntity> getAllUserBymybatis() {
        List<UserEntity> users = userMapper.getAll();
        for (UserEntity user : users) {
            System.out.println(user.toString());
        }
        return users;
    }

    /**
     * 与mybatis结合-xml方式
     * @return
     */
    @RequestMapping("/mybatis")
    public List<UserEntity> getAllUserBymybatis_xml() {
        List<UserEntity> users = userMapper_xml.getAll();
        for (UserEntity user : users) {
            System.out.println(user.toString());
        }
        return users;
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping("/mybatis")
    public UserEntity insertUser_xml(@RequestBody UserEntity user) {
        userMapper_xml.insert(user);
        return user;
    }

    /**
     * 根据id删除指定用户
     * @param id
     */
    @DeleteMapping("/mybatis/{id}")
    public void deleteUser_xml(@PathVariable("id") Long id) {
        userMapper_xml.delete(id);
    }

    /**
     * 根据id获取指定用户信息
     * @param id
     * @return
     */
    @GetMapping("/mybatis/{id}")
    public UserEntity getOneUser(@PathVariable("id") Long id) {
        UserEntity user = userMapper_xml.getOne(id);
        if (user != null) {
            return user;
        }
        return null;
    }

}
