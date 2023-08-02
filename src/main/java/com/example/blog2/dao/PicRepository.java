package com.example.blog2.dao;

import com.example.blog2.po.Pic;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qjl
 * @create 2023-06-20 12:04
 */
@Repository
public interface PicRepository extends JpaRepository<Pic,Long> {
    @Query("select pic_url from Pic ")
    List<String> getAllPic();
    @Delete("delete from Pic where filename = #{url}")
    void deleteByFilename(String url);
    @Insert("insert into Pic value (#{pic}.id,#{pic}.pic_url,#{pic}.filename)")
    void Filename(Pic pic);
    @Query("SELECT MAX(id) FROM Pic")
    Long SelectMaxId();

//    @Delete("delete from Pic where filename = #{url}")
//    Long deleteUrll(String url);
}
