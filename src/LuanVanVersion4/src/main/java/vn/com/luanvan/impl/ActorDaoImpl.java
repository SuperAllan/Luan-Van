package vn.com.luanvan.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.com.luanvan.dao.ActorDao;
import vn.com.luanvan.model.Actor;
import vn.com.luanvan.model.Loaiactor;

@Repository
public class ActorDaoImpl implements ActorDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public void add(Actor actor) {
		sessionFactory.getCurrentSession().save(actor);
	}

	@Transactional
	public void update(Actor actor) {
		sessionFactory.getCurrentSession().update(actor);
	}

	@Transactional
	public void delete(Actor actor) {
		sessionFactory.getCurrentSession().delete(actor);
	}

	@Transactional
	public void deleteByProject(int id) {
		String sql = "delete from Actor as a where a.project.projectid = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		query.executeUpdate();

	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Actor> getActorByProject(int id) {
		String sql = "from Actor as a where a.project.projectid = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		return (List<Actor>)query.list();

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Integer> countActor(Integer projectid) {
		List<Integer> count = new ArrayList<Integer>();
		String hql = "select count(a) FROM Actor as a where a.project.projectid = :id and a.loaiactor.loaiactorid= :loaiid";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", projectid);
		query.setParameter("loaiid", 1);
		count.addAll(query.list());
		query.setParameter("loaiid", 2);
		count.addAll(query.list());
		query.setParameter("loaiid", 3);
		count.addAll(query.list());
		return count;
	}

	@Transactional
	public List<Integer> tinhDiemTungActor(Integer projectid,
			List<Loaiactor> loaiActor) {
		List<Integer> countActor = countActor(projectid);
		List<Integer> ketQua = new ArrayList<Integer>();
		int temp = 0;
		for(int i = 0; i < loaiActor.size(); i++){
			temp = Integer.parseInt(String.valueOf(countActor.get(i)))*loaiActor.get(i).getTrongso();
			ketQua.add(temp);
		}
		return ketQua;
	}

	@Transactional
	public Integer tinhTongDiem(Integer projectid, List<Loaiactor> loaiActor) {
		List<Integer> diemActor = tinhDiemTungActor(projectid, loaiActor);
		int temp= 0;
		for(Integer inte : diemActor){
			temp += inte;
		}
		return temp;
	}

	@Transactional
	public Actor getActorByName(String name, int projectID) {
		String hql = "from Actor as a where a.nameofactor = :name and a.project.projectid = :projectID";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("projectID", projectID);
		query.setParameter("name", name);
		if (query.list().size() > 0) {
			return (Actor)query.list().get(0);
		}
		return null;
	}
	
	@Transactional
	public Actor getActorByID(int actorID) {
		String hql = "from Actor as a where a.actorid = :actorID";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("actorID", actorID);
		if (query.list().size() > 0) {
			return (Actor)query.list().get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Actor> getActorNameDefault(int projectID) {
		String hql = "from Actor as a where a.nameofactor like 'Actor:%' and a.project.projectid = :projectID";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("projectID", projectID);
		return query.list();
	}
}
