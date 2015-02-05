package vn.com.luanvan.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.com.luanvan.dao.ActorDao;
import vn.com.luanvan.model.Actor;

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

}
