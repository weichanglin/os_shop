package club.osnote.os_shop.controller;

import club.osnote.os_shop.dao.UserDao;
import club.osnote.os_shop.dao.UserMapper;
import club.osnote.os_shop.jwt.JwtUtils;
import club.osnote.os_shop.jwt.NeedLoginAnno;
import club.osnote.os_shop.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    UserMapper userMapper;


    @ResponseBody
    @RequestMapping("/login2")
    public String login(@RequestParam("id") Long Id, HttpServletResponse response) {
        UserDao userDao = new UserDao();
        userDao.setUid((Long) Id);
        userDao.setName("张三");
        userDao.setAge(25);
        userDao.setAddress("上海市浦东新区");
        String token = JwtUtils.createToken(Id);
        Cookie cookie = new Cookie(Constants.User_Token, token);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "id==>" + Id;
    }

    @ResponseBody
    @NeedLoginAnno
    @RequestMapping("/info")
    public Long info() {
        Long userId = (Long) httpServletRequest.getAttribute(Constants.User_ID);

        return userId;
    }


    @GetMapping("/login")
    public String index() {
        return "/user/login";
    }

    @ResponseBody
    @GetMapping("/test")
    public Object index2(@RequestParam("id") Long Id) {

        return userMapper.selectByPrimaryKey(Id);
    }
}
