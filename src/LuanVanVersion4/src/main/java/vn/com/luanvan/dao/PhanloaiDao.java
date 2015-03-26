package vn.com.luanvan.dao;

import java.util.List;

import vn.com.luanvan.model.Phanloai;

public interface PhanloaiDao {
	void add(Phanloai phanloai);
	
	void update(Phanloai phanloai);
	
	void delete(Phanloai phanloai);
	
	void deleteAll();
	
	void deleteByUsecaseActor(int ucid, int actorid);
	
	Phanloai getPhanLoaibyUsecase(int usecaseID);
	
	Phanloai getPhanLoaibyUsecaseActor(int usecaseID, int actorID);
	
	List<Phanloai> getPhanLoaibyActor(int actorID);
}
