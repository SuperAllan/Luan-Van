package vn.com.luanvan.dao;

import java.util.List;

import vn.com.luanvan.model.Chucnang;
import vn.com.luanvan.model.Project;

public interface ChucNangDao{
	void save(Chucnang chucnang);
	
	void delete(Chucnang chucnang);
	
	List<Chucnang> getChucNangFromData(Project project);
	
	Chucnang findChucNangByID( int id);
	
	void update(Chucnang chucnang);
}