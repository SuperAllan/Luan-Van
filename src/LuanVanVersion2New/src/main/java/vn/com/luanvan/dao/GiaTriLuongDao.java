package vn.com.luanvan.dao;

import java.util.List;

import vn.com.luanvan.model.Giatriluong;

public interface GiaTriLuongDao{
	void delete(Integer projectid);
	
	void save(Giatriluong giatri);
	
	List<Giatriluong> getListGiaTriLuong(Integer projectid);
}