package vn.com.luanvan.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.com.luanvan.dao.UsecaseDao;
import vn.com.luanvan.model.Bmt;
import vn.com.luanvan.model.Usecase;
@Repository
public class UsecaseDaoImpl implements UsecaseDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public void add(Usecase usecase) {
		sessionFactory.getCurrentSession().save(usecase);
	}

	@Transactional
	public void update(Usecase usecase) {
		sessionFactory.getCurrentSession().update(usecase);
	}

	@Transactional
	public void delete(Usecase usecase) {
		sessionFactory.getCurrentSession().delete(usecase);
	}
	
	@Transactional
	public void deleteByProject(int id) {
		String sql = "delete from Usecase as u where u.project.projectid = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	@Transactional
	public Usecase getUsecaseById(String id) {
		String sql = "from Usecase as b where b.usecaseid = :usecaseid";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("usecaseid", id);
		return (Usecase)query.list().get(0);
	}

	@Transactional
	public List<Integer> countBMT(Integer projectid) {
		List<Integer> count = new ArrayList<Integer>();
		String hql = "select count(a) FROM Usecase as a where a.project.projectid= :id and a.tinhtien = 1 and a.bmt.bmtid = :bmtid";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", projectid);
		for(int i = 1; i <= 9; i++){
			query.setParameter("bmtid", i);
			count.addAll(query.list());
		}
		return count;
	}

	@Transactional
	public List<Integer> tinhDiemTungUsecase(Integer projectid, List<Bmt> lists) {
		List<Integer> count = countBMT(projectid);
		List<Integer> ketQua = new ArrayList<Integer>();
		int temp = 0;
		for(int i = 0; i < count.size(); i++){
			temp =  (int) (Integer.parseInt(String.valueOf(count.get(i))) * lists.get(i).getTrongso() * lists.get(i).getHesobmt());
			ketQua.add(temp);
		}
		return ketQua;
	}

	@Transactional
	public Integer tongBMT(Integer projectid) {
		List<Integer> count = countBMT(projectid);
		int ketQua = 0;
		for(int i = 0; i < count.size(); i++){
			ketQua +=  Integer.parseInt(String.valueOf(count.get(i)));
		}
				
		return ketQua;
	}

	@Transactional
	public Integer tongDiemTungUsecase(Integer projectid, List<Bmt> lists) {
		List<Integer> diem = tinhDiemTungUsecase(projectid, lists);
		int ketQua = 0;
		for(int i = 0; i < diem.size(); i++){
			ketQua +=  Integer.parseInt(String.valueOf(diem.get(i)));
		}
		return ketQua;
	}
	
}
