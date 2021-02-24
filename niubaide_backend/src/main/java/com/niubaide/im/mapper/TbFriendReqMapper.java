package com.niubaide.im.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niubaide.im.pojo.bean.TbFriendReq;
import com.niubaide.im.pojo.example.TbFriendReqExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbFriendReqMapper extends BaseMapper<TbFriendReq> {
    int countByExample(TbFriendReqExample example);

    int deleteByExample(TbFriendReqExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbFriendReq record);

    int insertSelective(TbFriendReq record);

    List<TbFriendReq> selectByExample(TbFriendReqExample example);

    TbFriendReq selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbFriendReq record, @Param("example") TbFriendReqExample example);

    int updateByExample(@Param("record") TbFriendReq record, @Param("example") TbFriendReqExample example);

    int updateByPrimaryKeySelective(TbFriendReq record);

    int updateByPrimaryKey(TbFriendReq record);
}