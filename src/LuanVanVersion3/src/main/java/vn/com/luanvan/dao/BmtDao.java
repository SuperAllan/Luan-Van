package vn.com.luanvan.dao;

import java.util.List;

import vn.com.luanvan.model.Bmt;

public interface BmtDao {
	Bmt getBmtById(int bmtID);
	
	List<Bmt> getAll();
}
