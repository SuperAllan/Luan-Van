package vn.com.luanvan.dao;
import java.util.List;

import vn.com.luanvan.model.Phanloaichucnang;

public interface PhanLoaiChucNangDao{
	List<Phanloaichucnang> getListTenLoai();
	Phanloaichucnang findPhanLoaiByID(Integer id);
}