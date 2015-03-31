package vn.com.luanvan.dao;

import vn.com.luanvan.model.FileUC;

public interface FileUCDao{
	void save(FileUC file);
	
	void delete(FileUC file);
	
	public FileUC findFileByID(int id);
}