package com.depot.ex.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.depot.ex.admin.dto.ParkinfoallData;
import com.depot.ex.admin.entity.Parkinfoall;


public interface ParkinfoallDao extends BaseDao<Parkinfoall>{

	List<ParkinfoallData> findAllParkinfoall(@Param("page")int page,@Param("size")int size);

	ParkinfoallData findById(int id);

	int findAllParkinfoallCount(@Param("name")String name);

	List<ParkinfoallData> findAllParkinfoallByLike(@Param("page")int page, @Param("size")int size, @Param("name")String name);

	List<ParkinfoallData> findByCardNum(@Param("cardnum")String cardnum,@Param("name")String name);

	void updateCardnum(@Param("cardnum")String cardnum, @Param("cardnumNew")String cardnumNew);

	List<ParkinfoallData> findByCardNumByPage(@Param("page")int page, @Param("size")int size, @Param("cardnum")String cardnum, @Param("name")String name);

}
