package vn.com.luanvan.dao;

import java.util.List;

import vn.com.luanvan.model.Phichucnang;

public interface PhichucnangDao {
	void delete(Phichucnang phichucnang);
	
	void save(Phichucnang phichucnang);
	
	void update(Phichucnang phichucnang);
	
	List<Phichucnang> getListPhiChucNangByProjectID(int projectID);
	
	void deleteAll(int projectID);
	
	Phichucnang findByID(int id);
	
	Phichucnang fintByProjectID (int projectID, String motayeucau);
}
