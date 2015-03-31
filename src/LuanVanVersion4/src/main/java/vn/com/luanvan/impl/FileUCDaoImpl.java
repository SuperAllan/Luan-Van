package vn.com.luanvan.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.com.luanvan.dao.FileUCDao;
import vn.com.luanvan.model.FileUC;

@Repository
public class FileUCDaoImpl implements FileUCDao{
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public void save(FileUC file) {
		sessionFactory.getCurrentSession().save(file);
	}

	@Transactional
	public FileUC findFileByID(int id) {
		return (FileUC) sessionFactory.getCurrentSession().get(FileUC.class, id);
	}

	@Transactional
	public void delete(FileUC file) {
		sessionFactory.getCurrentSession().delete(file);
	}
	
}