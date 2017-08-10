package com.lk.auth.repository;

import com.lk.auth.domain.UsersRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * Created by Administrator on 2017/3/25.
 */
public interface IUsersRolesRepo extends JpaRepository<UsersRoles, Long>{
    Set<UsersRoles> findByUserId(String userId);
}
