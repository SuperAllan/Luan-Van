package vn.com.luanvan.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysql.fabric.xmlrpc.base.Array;

import vn.com.luanvan.dao.LuongDao;
import vn.com.luanvan.model.Giatriluong;
import vn.com.luanvan.model.Luong;

@Repository
public class LuongDaoImpl implements LuongDao{
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public List<Luong> getListLuong() {
		String hql = "FROM Luong";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	@Transactional
	public List<Integer> TinhLuongCoBan(int mucLuongNhaNuoc, List<Luong> luongs) {
		List<Integer> luongCoBan = new ArrayList<Integer>();
		int temp = 0;
		for(Luong luong : luongs){
			temp = (int) (luong.getHeso() * mucLuongNhaNuoc);
			luongCoBan.add(temp);
		}
		return luongCoBan;
	}

	@Transactional
	public List<Integer> TinhLuongPhu(int mucLuongNhaNuoc, List<Luong> luongs) {
		List<Integer> luongCoBan = TinhLuongCoBan(mucLuongNhaNuoc, luongs);
		List<Integer> luongPhu = new ArrayList<Integer>();
		int temp = 0;
		for(Integer inte : luongCoBan){
			temp = (int) (inte * 0.12);
			luongPhu.add(temp);
		}
		return luongPhu;
	}

	@Transactional
	public List<Integer> TinhCPKG(int mucLuongNhaNuoc, List<Luong> luongs) {
		List<Integer> luongCoBan = TinhLuongCoBan(mucLuongNhaNuoc, luongs);
		List<Integer> CPKG = new ArrayList<Integer>();
		int temp = 0;
		for(Integer inte : luongCoBan){
			temp = (int) (inte * 0.04);
			CPKG.add(temp);
		}
		return CPKG;
	}

	@Transactional
	public List<Integer> TinhBaoHiem(int mucLuongNhaNuoc, List<Luong> luongs, List<Giatriluong> list) {
		List<Integer> baoHiem = new ArrayList<Integer>(); 
		List<Integer> luongPhu = TinhLuongPhu(mucLuongNhaNuoc, luongs);
		List<Integer> luongCoBan = TinhLuongCoBan(mucLuongNhaNuoc, luongs);
		int temp = 0;
		for(int i = 0; i < luongCoBan.size(); i++){
			temp = (int) ((luongCoBan.get(i) + luongPhu.get(i) + list.get(i).getPckhuvuc()) * 0.23);
			baoHiem.add(temp);
		}
		return baoHiem;
	}

	@Transactional
	public List<Integer> TinhTong(int mucLuongNhaNuoc, List<Luong> luongs,  List<Giatriluong> list) {
		List<Integer> tong = new ArrayList<Integer>(); 
		List<Integer> luongPhu = TinhLuongPhu(mucLuongNhaNuoc, luongs);
		List<Integer> luongCoBan = TinhLuongCoBan(mucLuongNhaNuoc, luongs);
		List<Integer> CPKG = TinhCPKG(mucLuongNhaNuoc, luongs);
		List<Integer> baoHiem = TinhBaoHiem(mucLuongNhaNuoc, luongs, list);
		int temp = 0;
		for(int i = 0; i < luongCoBan.size(); i++){
			temp = (int) (luongCoBan.get(i) + luongPhu.get(i) + list.get(i).getPckhuvuc() + list.get(i).getLuongtangthem() + list.get(i).getPcluudong() + CPKG.get(i) + baoHiem.get(i));
			tong.add(temp);
		}
		return tong;
	}

	@Transactional
	public List<Integer> TinhCP1Ngay(int mucLuongNhaNuoc, List<Luong> luongs, List<Giatriluong> list) {
		List<Integer> CP1Ngay = new ArrayList<Integer>(); 
		List<Integer> tong = TinhTong(mucLuongNhaNuoc, luongs, list);
		int temp = 0;
		for(Integer inte : tong){
			temp = inte/20;
			CP1Ngay.add(temp);
		}
		return CP1Ngay;
	}

	@Transactional
	public List<Integer> TinhCP1Gio(int mucLuongNhaNuoc, List<Luong> luongs, List<Giatriluong> list) {
		List<Integer> CP1Gio = new ArrayList<Integer>(); 
		List<Integer> CP1Ngay = TinhCP1Ngay(mucLuongNhaNuoc, luongs, list);
		int temp = 0;
		for(Integer inte : CP1Ngay){
			temp = inte/8;
			CP1Gio.add(temp);
		}
		return CP1Gio;
	}
	
}