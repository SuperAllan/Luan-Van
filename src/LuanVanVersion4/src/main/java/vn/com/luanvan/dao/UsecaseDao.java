package vn.com.luanvan.dao;

import java.util.List;

import vn.com.luanvan.model.Actor;
import vn.com.luanvan.model.Bmt;
import vn.com.luanvan.model.Usecase;

public interface UsecaseDao {
	void add(Usecase usecase);
	
	void update(Usecase usecase);
	
	void delete(Usecase usecase);
	
	void deleteByProject(int id);
	
	Usecase getUsecaseByName(String name, int projectID);
	
	Usecase getUsecaseByID(int usecaseID);
	
	List<Usecase> getUsecaseNameDefault(int projectID);
	
	List<Integer> countBMT(Integer projectid);
	
	Integer tongBMT(Integer projectid);
	
	List<Integer> tinhDiemTungUsecase(Integer projectid, List<Bmt> lists);
	
	Integer tongDiemTungUsecase(Integer projectid, List<Bmt> lists);
	
	List<Usecase> getUsecaseByProject(int ProjectId);
	
	List<Usecase> searchUsecase(String noidung, int projectID);
}
