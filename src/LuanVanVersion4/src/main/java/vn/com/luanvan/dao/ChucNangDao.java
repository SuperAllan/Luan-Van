package vn.com.luanvan.dao;

import java.util.List;

import vn.com.luanvan.model.Chucnang;
import vn.com.luanvan.model.Project;

public interface ChucNangDao{
	void save(Chucnang chucnang);
	
	void deleteData (Integer projectid);
	
	List<Chucnang> getChucNangFromData(Project project);
	
}