package com.hy.traffic.document.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.traffic.document.entity.Document;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gwl
 * @since 2020-07-25
 */
@Mapper
public interface DocumentMapper extends BaseMapper<Document> {

    @Select("select * from document")
    List<Document> queryDocument();

    @Select("select * from document where id=#{value}")
    Document queryById(Integer id);

}
