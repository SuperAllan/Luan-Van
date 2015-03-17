package vn.com.luanvan.dao;

import java.util.List;

import vn.com.luanvan.model.Nhomuc;

public interface NhomucDao {
	void add(Nhomuc nhomuc);
	
	void update(Nhomuc nhomuc);
	
	void delete(Nhomuc nhomuc);
	
	void deleteByProject(int id);
	
	boolean hasNhomuc(String tenNhom, int projectId);
	
	Nhomuc getNhomucByName(String tenNhom, int projectId);
	
	List<Nhomuc> getNhomucByProject(int projectId);
}
