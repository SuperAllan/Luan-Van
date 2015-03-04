package vn.com.luanvan.dao;

import java.util.List;

import vn.com.luanvan.model.Nhomuc;

public interface NhomucDao {
	void add(Nhomuc nhomuc);
	
	void update(Nhomuc nhomuc);
	
	void delete(Nhomuc nhomuc);
	
	void deleteByProject(int id);
	
	boolean hasNhomuc(String tenNhom);
	
	Nhomuc getNhomucByName(String tenNhom);
	
	List<Nhomuc> getNhomucByProject(int projectId);
}
