package com.cong.item.dao;

import com.cong.item.pojo.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 759329342@qq.com
 * @since created  on  2017/11/27.
 */
@Mapper
public interface ItemMapper {

    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    @Select("select * from item where id = #{id}")
    Item findItemById(@Param("id") long id);
}
