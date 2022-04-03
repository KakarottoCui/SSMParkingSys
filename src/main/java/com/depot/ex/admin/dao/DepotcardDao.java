package com.depot.ex.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.depot.ex.admin.dto.DepotcardManagerData;
import com.depot.ex.admin.entity.Depotcard;
import com.depot.ex.admin.entity.ParkInfo;

public interface DepotcardDao extends BaseDao<Depotcard>{

	List<DepotcardManagerData> findAllDepotcard(@Param("cardnum")String cardnum,@Param("page")int page,@Param("size")int size);
	void save(Depotcard m);
	Depotcard findByCardnum(@Param("cardnum")String cardnum);
	Depotcard findByCardid(@Param("cardid")int cardid);
	void updateDepotcardBycardnum(Depotcard depotcard);
	void deleteDepotCard(@Param("cardnum")String cardnum);
	void addMoney(@Param("cardnum")String cardnum, @Param("money")double money);
	int findAllDepotcardCount(@Param("cardnum")String cardnum);
	List<DepotcardManagerData> findByCardId(int cardid);
	void updateCardnum(@Param("cardnum")String cardnum, @Param("cardnumNew")String cardnumNew);
}
