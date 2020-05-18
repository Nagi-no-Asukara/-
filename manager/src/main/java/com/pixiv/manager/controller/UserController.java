package com.pixiv.manager.controller;

import com.pixiv.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * pixiv用户管理
 */
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;




}
