package vn.com.luanvan.dao;

import java.util.List;

import vn.com.luanvan.model.Loaiactor;

public interface LoaiactorDao {
	Loaiactor getLoaiactorById(int loaiActorID);
	
	List<Loaiactor> getAll();
}
