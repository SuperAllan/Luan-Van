package vn.com.luanvan.dao;

import java.util.List;

import vn.com.luanvan.model.Xephangkythuat;

public interface XepHangKyThuatDao{
	void save(Xephangkythuat kyThuat);
	
	void delete(Integer projectid);
	
	List<Xephangkythuat> getListXepHangKyThuat(Integer projectid);
	
	public List<Float> TinhKetQua(Integer projectid);	
	
	float TongKetqua(Integer projectid);
}