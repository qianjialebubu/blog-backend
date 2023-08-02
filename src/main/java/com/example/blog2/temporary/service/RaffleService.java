package com.example.blog2.temporary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog2.temporary.dao.Raffle;

/**
 * @author qjl
 * @create 2023-07-02 16:56
 */
public interface RaffleService extends IService<Raffle> {
    Boolean getone();
}
