package vn.com.luanvan.dao;

import java.util.List;

import vn.com.luanvan.model.Trongsonoluc;

public interface TrongsonolucDao{
	List<Trongsonoluc> getAll();
	
	Trongsonoluc findByGiaTri(double giaTri);
	
	Trongsonoluc findByID(int trongsoID);
}