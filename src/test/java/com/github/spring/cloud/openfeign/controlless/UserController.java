package com.github.spring.cloud.openfeign.controlless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangyongxu
 */
@RestController
@RequestMapping("hllo")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public ResponseEntity<String> add(User user) {
        return ResponseEntity.ok("ok");
    }

    public ResponseEntity<User> get(String id) {
        User user = new User();
        user.setId("1");
        user.setName("zhangsan");
        user.setEmail("zhangsan@xxx.com");
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("ok");
    }

}
