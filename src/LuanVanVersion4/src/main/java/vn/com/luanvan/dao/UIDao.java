package vn.com.luanvan.dao;

import java.util.List;

import vn.com.luanvan.model.UI;
import vn.com.luanvan.model.Usecase;

public interface UIDao {
	void add(UI ui);
	
	void update(UI ui);
	
	void delete(UI ui);
	
	List<UI> getUIByProject(int projectID);
	
	UI getUIByName(String nameui, int projectID);
	
}
