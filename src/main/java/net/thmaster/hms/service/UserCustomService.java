package net.thmaster.hms.service;

import net.thmaster.hms.model.entity.UserCustom;
import net.thmaster.hms.model.req.UserCustomInfoRequest;

/**
 * @author master
 */
public interface UserCustomService {

    UserCustom get(Long userId);

    UserCustom update(Long userId, UserCustomInfoRequest info);

}
