package vn.com.luanvan.dao;

import java.util.List;

import vn.com.luanvan.model.Nhomchucnang;
import vn.com.luanvan.model.Project;

public interface NhomChucNangDao{
	void save(Nhomchucnang nhom);
	
	List<Nhomchucnang> getListNhomChucNang(Project project);
	
	void delete(Nhomchucnang nhom);
	
	void update(Nhomchucnang nhom);
	
	Nhomchucnang findNhomChucNangByID(int id);
	
	Nhomchucnang findNhomByProjectID(int projectid, String tennhom);
}