package com.lk.basic.centerykt.repository;

import com.lk.basic.centerykt.domain.BasicCenterYkt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/3/2.
 */
@Repository
public interface BasicCenterYktRepo extends CrudRepository<BasicCenterYkt, Long> {
    public BasicCenterYkt findByCenterNo(String centerNo);
}
