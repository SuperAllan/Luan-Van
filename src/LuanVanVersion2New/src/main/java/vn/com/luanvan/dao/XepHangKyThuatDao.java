package vn.com.luanvan.dao;

import java.util.ArrayList;
import java.util.List;

import vn.com.luanvan.model.Xephangkythuat;

public interface XepHangKyThuatDao{
	void save(Xephangkythuat kyThuat);
	
	void delete(Integer projectid);
	
	List<Xephangkythuat> getListXepHangKyThuat(Integer projectid);
	
	public List<Integer> TinhKetQua(Integer projectid);	
	
	Integer TongKetqua(Integer projectid);
}