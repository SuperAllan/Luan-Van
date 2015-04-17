package vn.com.luanvan.test;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import vn.com.luanvan.dao.ActorDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
public class ActorDaoImplTest {
	@Autowired
	private ActorDao actorDao;
	
	@Test
	@Transactional
	public void testDeleteByProject() {
//		actorDao.deleteByProject(18);
//		assertEquals( actorDao.getActorByProject(18).size() <= 0, true);
	}

//	@Test
//	public void testGetActorByProject() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCountActor() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testTinhDiemTungActor() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testTinhTongDiem() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetActorByName() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetActorByID() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetActorNameDefault() {
//		fail("Not yet implemented");
//	}

}
