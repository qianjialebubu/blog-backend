package com.example.blog2.temporary.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog2.temporary.dao.Raffle;
import com.example.blog2.temporary.mapper.RaffleMapper;
import com.example.blog2.temporary.service.RaffleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


/**
 * @program: myblog-backendv1.5
 * @description:
 * @author: qjl
 * @create: 2023-07-02 16:56
 **/
@Service
public class RaffleServiceImpl extends ServiceImpl<RaffleMapper, Raffle> implements RaffleService{
    @Autowired
    private RaffleMapper raffleMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public Boolean getone() {
        synchronized (this){

            Integer num = (Integer) redisTemplate.opsForValue().get("num");
            if(num<=0||num==null){
                return false;
            }
            num = num-1;
            redisTemplate.opsForValue().set("num", num);
            return true;
        }
//        if(count>0){
//            count --;
//            raffleMapper.deleteById(1);
//            Raffle newRaffle = new Raffle();
//            newRaffle.setId(1);
//            newRaffle.setCount(count);
//            raffleMapper.insert(newRaffle);
//            return true;
//        }else {
//            return false;
//        }
    }
}
