package vn.com.luanvan.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.com.luanvan.dao.XepHangKyThuatDao;
import vn.com.luanvan.model.Xephangkythuat;
@Repository
public class XepHangKyThuatDaoImpl implements XepHangKyThuatDao{
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public void save(Xephangkythuat kyThuat) {
		sessionFactory.getCurrentSession().save(kyThuat);
	}

	@Transactional
	public void delete(Integer projectid) {
		String hql = "DELETE FROM Xephangkythuat as a where a.project.projectid = :projectid";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("projectid", projectid);
		query.executeUpdate();
	}

	@Transactional
	public List<Xephangkythuat> getListXepHangKyThuat(Integer projectid) {
		String hql = "FROM Xephangkythuat as a where a.project.projectid = :projectid";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("projectid", projectid);
		return query.list();
	}

	@Transactional
	public List<Float> TinhKetQua(Integer projectid){
		List<Xephangkythuat> lists  = getListXepHangKyThuat(projectid);
		List<Float> ketqua = new ArrayList<Float>();
		float KQTemp = 0;
		for(Xephangkythuat xh : lists){
			KQTemp = (xh.getGiatrixephang() * xh.getHesokythuat().getTrongso());
			ketqua.add(KQTemp);
		}
		return ketqua;
	}

	@Transactional
	public float TongKetqua(Integer projectid) {
		List<Xephangkythuat> lists  = getListXepHangKyThuat(projectid);
		float KQTemp = 0;
		for(Xephangkythuat xh : lists){
			KQTemp += (xh.getGiatrixephang() * xh.getHesokythuat().getTrongso());
		}
		return KQTemp;
	}

	
}