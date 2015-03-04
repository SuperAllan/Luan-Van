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
import vn.com.luanvan.dao.NhomucDao;
import vn.com.luanvan.dao.PhanloaiDao;
import vn.com.luanvan.dao.ProjectDao;
import vn.com.luanvan.dao.UsecaseDao;
import vn.com.luanvan.model.Actor;
import vn.com.luanvan.model.Nhomuc;
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
	
	@Autowired
	NhomucDao nhomucDao;
	
	@RequestMapping(value = "/diagram/savediagramusecase", method = RequestMethod.POST)
	public @ResponseBody String addUsecase (@RequestParam(value="ucArray") List<String> ucArray,
				@RequestParam(value="nameProject") String nameProject,
				@RequestParam(value="sodo") String sodo,
				@RequestParam(value="actorArray") List<String> actorArray,
				Principal principal) {
		
		// Lay du an hien tai
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		
		// Clear all element on diagram when update
		actorDao.deleteByProject(project.getProjectid());
		
		// Clear all element on diagram when update
		usecaseDao.deleteByProject(project.getProjectid());
		
		// Clear all element nhomuc of project now
		nhomucDao.deleteByProject(project.getProjectid());
		
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
			uc.setUsecaseid(ucAttr[4]);
			if (ucAttr.length > 5) {
				if (!nhomucDao.hasNhomuc(ucAttr[5])) {
					Nhomuc nhomuc = new Nhomuc();
					nhomuc.setProject(project);
					nhomuc.setTennhom(ucAttr[5]);
					nhomucDao.add(nhomuc);
					uc.setNhomuc(nhomuc);
				} else {
					uc.setNhomuc(nhomucDao.getNhomucByName(ucAttr[5]));
				}
			}
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
						
						plDao.deleteByUsecaseActor(uc.getUsecaseid(), actor.getActorid());
						
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
	
	@RequestMapping(value = "/diagram/scoreactor")
	public @ResponseBody String  scoreActor(Principal principal, @RequestParam(value="nameProject") String nameProject) {
		
		int donGian = 0;
		int trungBinh = 0;
		int phucTap = 0;
		
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		List<Actor> actors = actorDao.getActorByProject(project.getProjectid());
		
		for (Actor a : actors) {
			int trongSo = a.getLoaiactor().getTrongso();
			if (trongSo == 1) {
				donGian ++;
			} else if (trongSo == 2){
				trungBinh ++;
			} else if (trongSo == 3) {
				phucTap ++;
			}
		}
		return donGian + "_" + trungBinh + "_" + phucTap;
	}
	
	@RequestMapping(value = "/diagram/scoreusecase")
	public @ResponseBody String  scoreUsecase(Principal principal, @RequestParam(value="nameProject") String nameProject) {
		
		int typeB = 0;
		int typeBSimple = 0;
		int typeBAverage = 0;
		int typeBComplex = 0;
		double scoreBSimple = 0;
		double scoreBAverage = 0;
		double scoreBComplex = 0;
		int typeM = 0;
		int typeMSimple = 0;
		int typeMAverage = 0;
		int typeMComplex = 0;
		double scoreMSimple = 0;
		double scoreMAverage = 0;
		double scoreMComplex = 0;
		int typeT = 0;
		int typeTSimple = 0;
		int typeTAverage = 0;
		int typeTComplex = 0;
		double scoreTSimple = 0;
		double scoreTAverage = 0;
		double scoreTComplex = 0;
		
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		List<Usecase> usecases = usecaseDao.getUsecaseByProject(project.getProjectid());
		
		for (Usecase u : usecases) {
			String type = u.getBmt().getTen().split("-")[0];
			String level = u.getBmt().getTen().split("-")[1];
			
			if (type.equals("B ") && u.getTinhtien() == 1) {
				typeB ++;
				if (level.equals(" Đơn giản") && u.getTinhtien() == 1) {
					typeBSimple ++;
				} else if (level.equals(" Trung bình") && u.getTinhtien() == 1){
					typeBAverage ++;
				} else if (level.equals(" Phức tạp") && u.getTinhtien() == 1) {
					typeBComplex ++;
				}
			} else if (type.equals("M ") && u.getTinhtien() == 1){
				typeM ++;
				if (level.equals(" Đơn giản") && u.getTinhtien() == 1) {
					typeMSimple ++;
				} else if (level.equals(" Trung bình") && u.getTinhtien() == 1){
					typeMAverage ++;
				} else if (level.equals(" Phức tạp") && u.getTinhtien() == 1) {
					typeMComplex ++;
				}
			} else if (type.equals("T ") && u.getTinhtien() == 1) {
				typeT ++;
				if (level.equals(" Đơn giản") && u.getTinhtien() == 1) {
					typeTSimple ++;
				} else if (level.equals(" Trung bình") && u.getTinhtien() == 1){
					typeTAverage ++;
				} else if (level.equals(" Phức tạp") && u.getTinhtien() == 1) {
					typeTComplex ++;
				}
			}
		}
		scoreBSimple = typeBSimple * 5;
		scoreBAverage = typeBAverage * 10;
		scoreBComplex = typeBComplex * 15;
		scoreMSimple = typeMSimple * 5 * 1.2;
		scoreMAverage = typeMAverage * 10 * 1.2;
		scoreMComplex = typeMComplex * 15 * 1.2;
		scoreTSimple = typeTSimple * 5 * 1.5;
		scoreTAverage = typeTAverage * 10 * 1.5;
		scoreTComplex = typeTComplex * 15 * 1.5;
		
		return typeB + "_" + typeM + "_" + typeT + "_"
			+ typeBSimple + "_" + typeBAverage + "_" + typeBComplex + "_"
			+ typeMSimple + "_" + typeMAverage + "_" + typeMComplex + "_"
			+ typeTSimple + "_" + typeTAverage + "_" + typeTComplex + "_"
			+ scoreBSimple + "_" + scoreBAverage + "_" + scoreBComplex + "_"
			+ scoreMSimple + "_" + scoreMAverage + "_" + scoreMComplex + "_"
			+ scoreTSimple + "_" + scoreTAverage + "_" + scoreTComplex + "_";
	}
	
}
