package club.osnote.os_shop.service;

import club.osnote.os_shop.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    TestDao testDao;

    public String sayhello(String name){
        return testDao.sayhello(name);
    }

    
    public void sayhello(){
        System.out.println(testDao.sayhello("5s..."));
    }
}
