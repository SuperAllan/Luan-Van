package vn.com.luanvan.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysql.fabric.xmlrpc.base.Array;

import vn.com.luanvan.dao.LuongDao;
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
	
}