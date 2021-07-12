package com.example.blog2.service;

import com.example.blog2.po.Essay;

import java.util.List;

/**
 * @author zhaomin_2017013792_CS181
 * @version 1.0
 * @date 2021/7/12 16:41
 */

public interface EssayService {
    List<Essay> listEssay();

    void deleteEssay(Long id);

    Essay saveEssay(Essay essay);

    Essay updateEssay(Long id,Essay essay);
}
