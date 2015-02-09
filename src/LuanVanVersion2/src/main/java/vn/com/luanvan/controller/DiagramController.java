package vn.com.luanvan.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.com.luanvan.dao.ActorDao;
import vn.com.luanvan.dao.BmtDao;
import vn.com.luanvan.dao.LoaiactorDao;
import vn.com.luanvan.dao.PhanloaiDao;
import vn.com.luanvan.dao.ProjectDao;
import vn.com.luanvan.dao.UsecaseDao;
import vn.com.luanvan.model.Actor;
import vn.com.luanvan.model.Phanloai;
import vn.com.luanvan.model.PhanloaiId;
import vn.com.luanvan.model.Project;
import vn.com.luanvan.model.Usecase;

@Controller
public class DiagramController {
	
	@Autowired
	BmtDao btmDao;
	
	@Autowired
	UsecaseDao usecaseDao;
	
	@Autowired
	ActorDao actorDao;
	
	@Autowired
	PhanloaiDao plDao;
	
	@Autowired
	LoaiactorDao loaiactorDao;
	
	@Autowired
	ProjectDao projectDao;
	
	@RequestMapping(value = "/diagram/savediagramusecase", method = RequestMethod.POST)
	public @ResponseBody String addUsecase (@RequestParam(value="ucArray") List<String> ucArray,
				@RequestParam(value="nameProject") String nameProject,
				@RequestParam(value="sodo") String sodo,
				@RequestParam(value="actorArray") List<String> actorArray,
				Principal principal) {
		
		// Luu so do vao du an
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		
		// Clear all Phanloai
		plDao.deleteAll();
		
		// Clear all element on diagram when update
		actorDao.deleteByProject(project.getProjectid());
		
		// Clear all element on diagram when update
		usecaseDao.deleteByProject(project.getProjectid());
		
		// Update element on diagram
		for (int i = 0; i < ucArray.size(); i++) {
			String[] ucAttr = ucArray.get(i).split("=");
			Usecase uc = new Usecase();
			uc.setProject(project);
			uc.setNameofuc(ucAttr[0]);
			uc.setMotauc(ucAttr[1]);
			uc.setBmt(btmDao.getBmtById(Integer.parseInt(ucAttr[2])));
			if (ucAttr[3].contains("true")) {
				uc.setTinhtien(1);
			} else {
				uc.setTinhtien(0);
			}
			uc.setUsecaseid(ucAttr[4]);;
			usecaseDao.add(uc);
		}
		
		// Update element on diagram
		for (int i = 0; i < actorArray.size(); i++) {
			String[] actorAttr = actorArray.get(i).split("=");
			Actor actor = new Actor();
			actor.setProject(project);
			actor.setNameofactor(actorAttr[0]);
			actor.setMotaactor(actorAttr[1]);
			actor.setLoaiactor(loaiactorDao.getLoaiactorById(Integer.parseInt(actorAttr[2])));
	
			
			actorDao.add(actor);
			
			if (actorAttr.length > 3) {
				Set<Phanloai> pls = new HashSet<Phanloai>(0);
				String[] roles = actorAttr[3].split("/");
				for (int j = 0; j < roles.length; j++) {
					if (roles[j] != "") {
						String[] attrRole = roles[j].split("_");
						
						Usecase uc = usecaseDao.getUsecaseById(attrRole[1]);
						
						PhanloaiId plid = new PhanloaiId();
						plid.setActorid(actor.getActorid());
						plid.setUsecaseid(uc.getUsecaseid());
						
						Phanloai pl = new Phanloai();
						pl.setId(plid);
						pl.setActor(actor);
						pl.setUsecase(uc);
						pl.setVaitro(Integer.parseInt(attrRole[0]));
						plDao.add(pl);
						pls.add(pl);
					}
				}
				
				actor.setPhanloais(pls);
				actorDao.update(actor);
			}
		}
		
		project.setSodo(sodo);
		projectDao.save(project);
		
		return "";
	}
}
