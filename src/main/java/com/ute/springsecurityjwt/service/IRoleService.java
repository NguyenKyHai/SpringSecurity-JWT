package com.ute.springsecurityjwt.service;

import com.ute.springsecurityjwt.model.Role;
import com.ute.springsecurityjwt.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);
}
