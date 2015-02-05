package vn.com.luanvan.dao;

import java.util.List;

import vn.com.luanvan.model.Actor;

public interface ActorDao {
	void add(Actor actor);
	
	void update(Actor actor);
	
	void delete(Actor actor);
	
	void deleteByProject(int id);

	List<Actor> getActorByProject(int id);
}
