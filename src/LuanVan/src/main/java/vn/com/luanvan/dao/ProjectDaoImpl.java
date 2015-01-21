package vn.com.luanvan.dao;

import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.com.luanvan.model.Project;
import vn.com.luanvan.model.User;

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
		System.out.print(username);
		System.out.print(projectName);
		String hql = "from Project as a where  a.user.username= :username and a.name= :projectName";
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

	@Transactional
	public List<Project> getListProject(String username, int status) {
		List<Project> projects = new ArrayList<Project>();
		String hql = "from Project as a where a.user.username= :username and a.status= :status"; 
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("status", status);
		projects = query.list();
		return projects;
	}
	
}