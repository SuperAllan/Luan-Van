package vn.com.luanvan.dao;

import java.util.List;

import vn.com.luanvan.model.Giatriluong;
import vn.com.luanvan.model.Luong;

public interface LuongDao{
	List<Luong> getListLuong();
	
	List<Integer> TinhLuongCoBan(int mucLuongNhaNuoc, List<Luong> luongs);
	
	List<Integer> TinhLuongPhu(int mucLuongNhaNuoc, List<Luong> luongs);
	
	List<Integer> TinhCPKG(int mucLuongNhaNuoc, List<Luong> luongs);
	
	List<Integer> TinhBaoHiem(int mucLuongNhaNuoc, List<Luong> luongs, List<Giatriluong> list);
	
	List<Integer> TinhTong(int mucLuongNhaNuoc, List<Luong> luongs, List<Giatriluong> list);
	
	List<Integer> TinhCP1Ngay(int mucLuongNhaNuoc, List<Luong> luongs, List<Giatriluong> list);
	
	List<Integer> TinhCP1Gio(int mucLuongNhaNuoc, List<Luong> luongs, List<Giatriluong> list);
	
	
}
