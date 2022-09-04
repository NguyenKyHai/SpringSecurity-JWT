package com.ute.springsecurityjwt.service.impl;

import com.ute.springsecurityjwt.model.Role;
import com.ute.springsecurityjwt.model.RoleName;
import com.ute.springsecurityjwt.repository.IRoleRepository;
import com.ute.springsecurityjwt.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
