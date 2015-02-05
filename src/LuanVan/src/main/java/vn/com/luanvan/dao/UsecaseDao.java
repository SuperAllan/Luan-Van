package vn.com.luanvan.dao;

import vn.com.luanvan.model.Usecase;

public interface UsecaseDao {
	void add(Usecase usecase);
	
	void update(Usecase usecase);
	
	void delete(Usecase usecase);
	
	void deleteByProject(int id);
	
	Usecase getUsecaseById(String id);
}
