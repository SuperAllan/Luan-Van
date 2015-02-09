package vn.com.luanvan.dao;

import java.util.List;

import vn.com.luanvan.model.Luong;

public interface LuongDao{
	List<Luong> getListLuong();
	
	List<Integer> TinhLuongCoBan(int mucLuongNhaNuoc, List<Luong> luongs);
}
