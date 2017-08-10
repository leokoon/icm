package com.lk.auth.repository;

import com.lk.auth.domain.Auth;
import com.lk.auth.domain.Role;
import com.lk.auth.domain.RolesAuths;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */
public interface IRolesAuthsRepo extends JpaRepository<RolesAuths, Long> {
    @Query(value = "SELECT * FROM Role WHERE role_Id = (SELECT role_Id FROM Roles_Auths WHERE auth_Id = ?1) ORDER BY role_id", nativeQuery = true)
    List<Role> findByAuthId(String authId);

    @Query(value = "SELECT * FROM Auth a WHERE exists (select role_id from roles_auths where auth_id = a.auth_id and role_Id = ?1) ORDER BY auth_id", nativeQuery = true)
    List<Auth> findAuthByRoleId(String roleId);

    List<RolesAuths> findByRoleId(String roleId);
}
