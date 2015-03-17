package vn.com.luanvan.dao;

import vn.com.luanvan.model.Phanloai;

public interface PhanloaiDao {
	void add(Phanloai phanloai);
	
	void update(Phanloai phanloai);
	
	void delete(Phanloai phanloai);
	
	void deleteAll();
	
	void deleteByUsecaseActor(String ucid, int actorid);
	
	Phanloai getPhanLoaibyUsecase(String usecaseID);
}
