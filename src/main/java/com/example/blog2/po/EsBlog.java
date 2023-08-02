package com.example.blog2.po;

/**
 * @program: springtest
 * @description: 商品类
 * @author: qjl
 * @create: 2023-06-25 08:57
 **/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "esblog", shards = 3, replicas = 1)
public class EsBlog {
    //必须有 id,这里的 id 是全局唯一的标识，等同于 es 中的"_id"
    @Id
    private Long id;//商品唯一标识

    /**
     * type : 字段数据类型
     * analyzer : 分词器类型
     * index : 是否索引(默认:true)
     * Keyword : 短语,不进行分词
     */
//    @Field(type = FieldType.Text)
//    @Field(type = FieldType.Text, analyzer = "Standard")
    @Field(type = FieldType.Text  , searchAnalyzer = "ik_smart", analyzer = "ik_max_word")
    private String title;//博客名称

    @Field(type = FieldType.Text  , searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String content;//博客内容

    @Field(type = FieldType.Keyword)
    private String category;//分类名称

    @Field(type = FieldType.Double)
    private Double price;//商品价格


    @Field(type = FieldType.Keyword, index = false)
    private String images;//图片地址
}

