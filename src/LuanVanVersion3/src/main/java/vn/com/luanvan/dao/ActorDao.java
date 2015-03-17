package vn.com.luanvan.dao;

import java.util.List;

import vn.com.luanvan.model.Actor;
import vn.com.luanvan.model.Loaiactor;

public interface ActorDao {
	void add(Actor actor);
	
	void update(Actor actor);
	
	void delete(Actor actor);
	
	void deleteByProject(int id);

	List<Actor> getActorByProject(int id);
	
	List<Integer> countActor(Integer projectid);
	
	List<Integer> tinhDiemTungActor(Integer projectid, List<Loaiactor> loaiActor);
	
	Integer tinhTongDiem(Integer projectid, List<Loaiactor> loaiActor);
}
