package vn.com.luanvan.dao;

import java.util.List;

import vn.com.luanvan.model.Xephangmoitruong;

public interface XepHangMoiTruongDao{
	void save(Xephangmoitruong kyThuat);
	
	void delete(Integer projectid);
	
	List<Xephangmoitruong> getListXepHangMoiTruong(Integer projectid);
	
	public List<Float> TinhKetQuaMoiTruong(Integer projectid);
	
	float TongKetQuaMoiTruong(Integer projectid);
	
	float TongOnDinh(Integer projectid);
	
	float tinhNoiSuyLaoDong(Integer projectid);
}