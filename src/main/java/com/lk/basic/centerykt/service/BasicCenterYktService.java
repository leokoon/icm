package com.lk.basic.centerykt.service;

import com.lk.basic.centerykt.domain.BasicCenterYkt;
import com.lk.basic.centerykt.repository.BasicCenterYktRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/3/2.
 */
@Service
public class BasicCenterYktService {
    @Autowired
    private BasicCenterYktRepo basicCenterYktRepo;

    public Object findAll(){
        return basicCenterYktRepo.findAll();
    }

    public BasicCenterYkt findById(String centerNo){
        return basicCenterYktRepo.findByCenterNo(centerNo);
    }

    public BasicCenterYkt save(BasicCenterYkt basicCenterYkt){
        return basicCenterYktRepo.save(basicCenterYkt);
    }

}
