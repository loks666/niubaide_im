package com.niubaide.im.mapper;

import com.niubaide.im.pojo.bean.TbChatRecord;
import com.niubaide.im.pojo.example.TbChatRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fly
 */
public interface TbChatRecordMapper {
    int countByExample(TbChatRecordExample example);

    int deleteByExample(TbChatRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(TbChatRecord record);

    int insertSelective(TbChatRecord record);

    List<TbChatRecord> selectByExample(TbChatRecordExample example);

    TbChatRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbChatRecord record, @Param("example") TbChatRecordExample example);

    int updateByExample(@Param("record") TbChatRecord record, @Param("example") TbChatRecordExample example);

    int updateByPrimaryKeySelective(TbChatRecord record);

    int updateByPrimaryKey(TbChatRecord record);
}