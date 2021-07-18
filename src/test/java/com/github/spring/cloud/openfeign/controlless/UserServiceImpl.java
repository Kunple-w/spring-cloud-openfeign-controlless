package com.github.spring.cloud.openfeign.controlless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author wangyongxu
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public boolean add(User user) {
        logger.info("add user: {}", user);
        return true;
    }

    @Override
    public User get(String id) {
        User user = new User();
        user.setId("1");
        user.setName("zhangsan");
        user.setEmail("zhangsan@xxx.com");
        return user;
    }

    @Override
    public void logout() {
        logger.info("logout user");
    }
}
