package vn.com.luanvan.dao;
import java.util.List;

import vn.com.luanvan.model.Mucdo;
public interface MucDoDao{
	List<Mucdo> getListMucDo();
	Mucdo findMucDoByID(Integer id);
}