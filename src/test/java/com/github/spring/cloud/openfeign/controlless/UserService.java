package com.github.spring.cloud.openfeign.controlless;

import com.github.spring.cloud.openfeign.controlless.annotation.ControllerlessApi;

/**
 * @author wangyongxu
 */
@ControllerlessApi
public interface UserService {

    boolean add(User user);

    User get(String id);

    void logout();

}
