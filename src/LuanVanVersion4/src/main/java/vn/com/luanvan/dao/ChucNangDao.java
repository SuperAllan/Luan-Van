package vn.com.luanvan.dao;


import vn.com.luanvan.model.Chucnang;

public interface ChucNangDao{
	void save(Chucnang chucnang);
	
	void delete(Chucnang chucnang);
	
	Chucnang findChucNangByID( int id);
	
	void update(Chucnang chucnang);
	
	Chucnang findByNhomID (int nhomid, String motayeucau);
}