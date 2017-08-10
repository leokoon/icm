package com.lk.auth.repository;

import com.lk.auth.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/3/25.
 */
public interface IRoleRepo extends JpaRepository<Role, Long>{
    Role findByRoleId(String roleId);
}
