package com.shield.hczz.base.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.shield.hczz.base.domain.PzResult;

public interface PzResultMapper {
    int deleteByPrimaryKey(String resultId);

    int insert(PzResult record);

    int insertSelective(PzResult record);

    PzResult selectByPrimaryKey(String resultId);

    List<PzResult> selectByPzid(String resultId);

    int updateByPrimaryKeySelective(PzResult record);

    int updateByPrimaryKey(PzResult record);

    List<Map<String, Object>> selectResultByClueid(Map<String, Object> map);

	int updateResultByPz(String pzid);

	//根据resultId userId删除记录
	Integer remove(Map<String,String> map);

	//根据resultId查询
	Map<String, Object> selectByResultId(@Param("resultId")String resultId);

	//根据resultId修改
	Integer updateByResultId(@Param("model")PzResult result);

	//根据resultId查询此id下全部附件
	List<String> selectAtt(@Param("resultId")String resultId);
	
	//根据clueId查询此id下全部附件,MODULE_TYPE = 1
	List<String> selectatt(@Param("resultId")String resultId);

	//根据attId删除
	Integer deleteAtt(@Param("attId")String attId);

	//根据attId查询
	Map<String, String> selectAttById(@Param("attId")String attId);

}