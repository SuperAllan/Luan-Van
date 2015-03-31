package vn.com.luanvan.dao;

import java.util.List;

import vn.com.luanvan.model.UIUsecase;

public interface UIUsecaseDao {
	void save(UIUsecase uiUsecase);

	void delete(UIUsecase uiUsecase);

	void update(UIUsecase uiUsecase);
	
	UIUsecase getUIUsecaseByUIIDUsecseID(int uiid, int usecaseid);
	
	void deleteAllByID(int uiid);
	
	boolean hasUsecaseIDOfUIID(int usecaseID, int uiID);
	
	List<UIUsecase> getUIUsecaseByUsecaseID(int usecaseid);
}
