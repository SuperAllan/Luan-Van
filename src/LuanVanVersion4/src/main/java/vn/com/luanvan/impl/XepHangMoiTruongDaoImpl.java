package vn.com.luanvan.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.com.luanvan.dao.XepHangMoiTruongDao;
import vn.com.luanvan.model.Xephangkythuat;
import vn.com.luanvan.model.Xephangmoitruong;

@Repository
public class XepHangMoiTruongDaoImpl implements XepHangMoiTruongDao{
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public void save(Xephangmoitruong moitruong) {
		sessionFactory.getCurrentSession().save(moitruong);
	}

	@Transactional
	public void delete(Integer projectid) {
		String hql = "DELETE FROM Xephangmoitruong as a where a.project.projectid = :projectid";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("projectid", projectid);
		query.executeUpdate();
	}

	@Transactional
	public List<Xephangmoitruong> getListXepHangMoiTruong(Integer projectid) {
		String hql = "FROM Xephangmoitruong as a where a.project.projectid = :projectid";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("projectid", projectid);
		return query.list();
	}

	@Transactional
	public List<Float> TinhKetQuaMoiTruong(Integer projectid) {
		List<Xephangmoitruong> lists  = getListXepHangMoiTruong(projectid);
		List<Float> ketqua = new ArrayList<Float>();
		float KQTeamp = 0;
		for(Xephangmoitruong xh : lists){
			KQTeamp = (xh.getGiatrixephang() * xh.getHesomoitruong().getTrongso());
			ketqua.add(KQTeamp);
		}
		return ketqua;
	}

	@Transactional
	public float TongKetQuaMoiTruong(Integer projectid) {
		List<Xephangmoitruong> lists  = getListXepHangMoiTruong(projectid);
		float KQTemp = 0;
		for(Xephangmoitruong xh : lists){
			KQTemp += (xh.getGiatrixephang() * xh.getHesomoitruong().getTrongso());
		}
		return KQTemp;
	}

	@Transactional
	public float TongOnDinh(Integer projectid) {
		List<Xephangmoitruong> lists  = getListXepHangMoiTruong(projectid);
		float KQTemp = 0;
		for(Xephangmoitruong xh : lists){
			KQTemp += xh.getOndinh();
		}
		return KQTemp;
	}

	@Transactional
	public float tinhNoiSuyLaoDong(Integer projectid) {
		float temp = TongOnDinh(projectid);
		if(temp < 1){
			return 48;
		}else{
			if(temp >= 1 && temp < 3){
				return 32;
			}
		}
		return 20;
	}
	
}