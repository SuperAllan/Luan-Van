package vn.com.luanvan.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.com.luanvan.dao.ProjectDao;
import vn.com.luanvan.model.Project;

@Repository
public class ProjectDaoImpl implements ProjectDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void save(Project project) {
		sessionFactory.getCurrentSession().update(project);
		
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public boolean checkProjectName(String username, String projectName) {
		List<Project> project = new ArrayList<Project>();
		String hql = "from Project as a where  a.user.username= :username and a.tenproject= :projectName";
		Query query = (Query) sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("projectName", projectName);
		project = query.list();

		if (project.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	public void add(Project project) {
		sessionFactory.getCurrentSession().save(project);
		
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Project> getListProject(String username, int status) {
		String hql = "from Project as a where a.user.username= :username and a.trangthai= :status"; 
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("status", status);
		return query.list();
	}

	@Transactional
	public Project findProjectByName(String username,String projectName) {
		String hql = "from Project as a where a.user.username= :username and a.tenproject= :name"; 
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("name", projectName);
		return (Project) query.list().get(0);
	}

	@Transactional
	public void delete(Project project) {
		sessionFactory.getCurrentSession().delete(project);
	}

	@Transactional
	public Project findProjectByID(int projectID) {
		return (Project) sessionFactory.getCurrentSession().get(Project.class, projectID);
	}
	
}