package com.lk.auth.repository;

import com.lk.auth.domain.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/4/1.
 */
public interface IAuthRepo extends JpaRepository<Auth, Long> {
    Auth findByAuthUrl(String url);
    Auth findByAuthId(String authId);
}
