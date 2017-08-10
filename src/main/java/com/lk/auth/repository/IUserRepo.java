package com.lk.auth.repository;

import com.lk.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/3/25.
 */
public interface IUserRepo extends JpaRepository<User, Long>{
    User findByUsername(String username);
}
