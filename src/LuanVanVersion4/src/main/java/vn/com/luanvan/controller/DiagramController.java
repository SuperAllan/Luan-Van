package vn.com.luanvan.controller;

import java.io.File;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.com.luanvan.dao.ActorDao;
import vn.com.luanvan.dao.BmtDao;
import vn.com.luanvan.dao.DiagramActorDao;
import vn.com.luanvan.dao.DiagramDao;
import vn.com.luanvan.dao.DiagramUsecaseDao;
import vn.com.luanvan.dao.FileUCDao;
import vn.com.luanvan.dao.LoaiactorDao;
import vn.com.luanvan.dao.NhomucDao;
import vn.com.luanvan.dao.PhanloaiDao;
import vn.com.luanvan.dao.ProjectDao;
import vn.com.luanvan.dao.UIUsecaseDao;
import vn.com.luanvan.dao.UsecaseDao;
import vn.com.luanvan.dao.UserDao;
import vn.com.luanvan.model.Actor;
import vn.com.luanvan.model.Diagram;
import vn.com.luanvan.model.DiagramActor;
import vn.com.luanvan.model.DiagramActorId;
import vn.com.luanvan.model.DiagramUsecase;
import vn.com.luanvan.model.DiagramUsecaseId;
import vn.com.luanvan.model.FileUC;
import vn.com.luanvan.model.Nhomuc;
import vn.com.luanvan.model.Phanloai;
import vn.com.luanvan.model.PhanloaiId;
import vn.com.luanvan.model.Project;
import vn.com.luanvan.model.UIUsecase;
import vn.com.luanvan.model.Usecase;
import vn.com.luanvan.model.User;

@Controller
public class DiagramController {
	
	@Autowired
	BmtDao btmDao;
	
	@Autowired
	UsecaseDao usecaseDao;
	
	@Autowired
	ActorDao actorDao;
	
	@Autowired
	PhanloaiDao phanLoaiDao;
	
	@Autowired
	LoaiactorDao loaiactorDao;
	
	@Autowired
	ProjectDao projectDao;
	
	@Autowired
	NhomucDao nhomucDao;
	
	@Autowired
	DiagramDao diagramDao;
	
	@Autowired
	DiagramActorDao diagramActorDao;
	
	@Autowired
	DiagramUsecaseDao diagramUsecaseDao;
	
	@Autowired
	BmtDao bmtDao;
	
	@Autowired
	UIUsecaseDao uiUsecaseDao;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	private FileUCDao fileUCDao;
	
	
	@RequestMapping(value = "/diagram/newdiagram")
	public String newDiagram(Principal principal, Model model) {
		String projectName = (String) request.getParameter("nameProject");
		String username = principal.getName();
		Project project = projectDao.findProjectByName(username, projectName);
		List<Actor> actors = actorDao.getActorByProject(project.getProjectid());
		List<Usecase> usecases = usecaseDao.getUsecaseByProject(project.getProjectid());
		User user = userDao.findUserbyUserName(username);
		model.addAttribute("user", user);
		model.addAttribute("project", project);
		model.addAttribute("actors", actors);
		model.addAttribute("usecases", usecases);
		model.addAttribute("bmts", bmtDao.getAll());
		model.addAttribute("loaiactors", loaiactorDao.getAll());
		return "newdiagram";
	}
	
	@RequestMapping(value = "/diagram/viewdiagram")
	public String viewDiagram(Principal principal, Model model) {
		String projectName = (String) request.getParameter("nameProject");
		String nameDiagram = (String) request.getParameter("nameDiagram");
		String username = principal.getName();
		Project project;
		Diagram diagram;
		try{
			project = projectDao.findProjectByName(username, projectName);
			diagram = diagramDao.getDiagramByName(nameDiagram, project.getProjectid());
		}catch(Exception e){
			return "404";
		}
		List<Actor> actors = actorDao.getActorByProject(project.getProjectid());
		List<Usecase> usecases = usecaseDao.getUsecaseByProject(project.getProjectid());
		
		
		if (diagram != null) {
			model.addAttribute("path", diagram.getPath());
			model.addAttribute("nameDiagram", nameDiagram);
		} else {
			model.addAttribute("path", "");
			model.addAttribute("nameDiagram", "");
		}
		User user = userDao.findUserbyUserName(username);
		model.addAttribute("user", user);
		model.addAttribute("project", project);
		model.addAttribute("actors", actors);
		model.addAttribute("usecases", usecases);
		model.addAttribute("bmts", bmtDao.getAll());
		model.addAttribute("loaiactors", loaiactorDao.getAll());
		return "newdiagram";
	}
	
	@RequestMapping(value = "/diagram/creatediagram", produces="text/plain; charset=utf-8")
	public @ResponseBody String createDiagram(
			@RequestParam(value="nameProject") String nameProject,
			@RequestParam(value="nameDiagram") String nameDiagram,
			Principal principal) {

		// Get project
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		
		if (diagramDao.getDiagramByName(nameDiagram, project.getProjectid()) != null) {
			return "";
		}

		Diagram diagram = new Diagram();
		diagram.setProject(project);
		diagram.setNamediagram(nameDiagram);
		diagramDao.save(diagram);
		
		return "1";
	}
	
    @RequestMapping(value="/diagram/getinfoactor", produces="text/plain; charset=utf-8")
	public @ResponseBody String getInfoActor(
			@RequestParam(value="nameProject") String nameProject,
			@RequestParam(value="id") String id,
			Principal principal) {

		Actor actor = actorDao.getActorByID(Integer.parseInt(id));
		List<Phanloai> phanLoais = phanLoaiDao.getPhanLoaibyActor(actor.getActorid());
		String result = "{\"actor\":";
		result += "{\"mota\": \"" + actor.getMotaactor() + "\",";
		result += "\"mucdo\": \"" + actor.getLoaiactor().getLoaiactorid() + "\",";
		result += "\"phanloai\": [";
		if (phanLoais.size() > 0) {
			for (int i = 0; i < phanLoais.size() - 1; i++) {
				result += "{\"id\": \"" + phanLoais.get(i).getUsecase().getUsecaseid() + "\",";
				result += "\"name\": \"" + phanLoais.get(i).getUsecase().getNameofuc() + "\",";
				result += "\"vaitro\": \"" + phanLoais.get(i).getVaitro() + "\"},";
			}
			result += "{\"id\": \"" + phanLoais.get(phanLoais.size() - 1).getUsecase().getUsecaseid() + "\",";
			result += "\"name\": \"" + phanLoais.get(phanLoais.size() - 1).getUsecase().getNameofuc() + "\",";
			result += "\"vaitro\": \"" + phanLoais.get(phanLoais.size() - 1).getVaitro() + "\"}";
		}
		result += "]}}";
		return result;
	}
	
	@RequestMapping(value = "/diagram/getinfousecase", produces="text/plain; charset=utf-8")
	public @ResponseBody String getInfoUsecase(
			@RequestParam(value="nameProject") String nameProject,
			@RequestParam(value="id") String id,
			Principal principal) {
		
		// Get project
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		deleteNhomucNoChild(project.getProjectid());
		List<Nhomuc> nhomucs = nhomucDao.getNhomucByProject(project.getProjectid());
		Usecase usecase = usecaseDao.getUsecaseByID(Integer.parseInt(id));
		List<UIUsecase> uiUsecases = uiUsecaseDao.getUIUsecaseByUsecaseID(usecase.getUsecaseid());
		String result = "{\"usecase\":";
		result += "{\"mota\": \"" + usecase.getMotauc() + "\",";
		result += "\"tinhtien\": \"" + usecase.getTinhtien() + "\",";
		result += "\"mucdo\": \"" + usecase.getBmt().getBmtid() + "\",";
		result += "\"nhomid\": \"" + usecase.getNhomuc().getNhomucid() + "\",";
		result += "\"dsnhom\": [";
		if (nhomucs.size() > 0) {
			for (int i = 0; i < nhomucs.size() - 1; i++) {
				result += "{\"id\": \"" + nhomucs.get(i).getNhomucid() + "\",";
				result += "\"name\": \"" + nhomucs.get(i).getTennhom() + "\"},";
			}
			result += "{\"id\": \"" + nhomucs.get(nhomucs.size() - 1).getNhomucid() + "\",";
			result += "\"name\": \"" + nhomucs.get(nhomucs.size() - 1).getTennhom() + "\"}";
		}
		result += "],";
		result += "\"dsui\": [";
		if (uiUsecases.size() > 0) {
			for (int i = 0; i < uiUsecases.size() - 1; i++) {
				result += "{\"id\": \"" + uiUsecases.get(i).getUi().getUiid() + "\",";
				result += "\"name\": \"" + uiUsecases.get(i).getUi().getNameui() + "\"},";
			}
			result += "{\"id\": \"" + uiUsecases.get(uiUsecases.size() - 1).getUi().getUiid() + "\",";
			result += "\"name\": \"" + uiUsecases.get(uiUsecases.size() - 1).getUi().getNameui() + "\"}";
		}
		result += "]}}";
		return result;
	}
	
	@RequestMapping(value = "/diagram/listactor", produces="text/plain; charset=utf-8")
	public @ResponseBody String listActor(@RequestParam(value="nameProject") String nameProject, Principal principal) {
		// Lay du an hien tai
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		List<Actor> actors = actorDao.getActorByProject(project.getProjectid());
		String result = "{\"actor\":[";
		if (actors.size() > 0) {
			for (int i = 0; i < actors.size() - 1; i++) {
				result += "{\"name\": \"" + actors.get(i).getNameofactor() + "\",";
				result += "\"id\": \"" + actors.get(i).getActorid() + "\"}, ";
			}
			result += "{\"name\": \"" + actors.get(actors.size() - 1).getNameofactor() + "\",";
			result += "\"id\": \"" + actors.get(actors.size() - 1).getActorid() + "\"}";
		}
		result += "]}";
		return result;
	}
	
	@RequestMapping(value = "/diagram/listusecase", produces="text/plain; charset=utf-8")
	public @ResponseBody String listUsecase(@RequestParam(value="nameProject") String nameProject, Principal principal) {
		// Lay du an hien tai
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		List<Usecase> usecases = usecaseDao.getUsecaseByProject(project.getProjectid());
		String result = "{\"usecase\":[";
		if (usecases.size() > 0) {
			for (int i = 0; i < usecases.size() - 1; i++) {
				result += "{\"name\": \"" + usecases.get(i).getNameofuc() + "\",";
				result += "\"id\": \"" + usecases.get(i).getUsecaseid() + "\"}, ";
			}
			result += "{\"name\": \"" + usecases.get(usecases.size() - 1).getNameofuc() + "\",";
			result += "\"id\": \"" + usecases.get(usecases.size() - 1).getUsecaseid() + "\"}";
		}
		result += "]}";
		return result;
	}
	
	@RequestMapping(value = "/diagram/renamediagram", produces="text/plain; charset=utf-8")
	public @ResponseBody String renameDiagram(@RequestParam(value="nameProject") String nameProject,
			@RequestParam(value="nameDiagramOld") String nameDiagramOld,
			@RequestParam(value="nameDiagramNew") String nameDiagramNew,
			Principal principal) {
		
		// Lay du an hien tai
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		
		if (nameDiagramOld.equals(nameDiagramNew)) {
			return "1";
		}
		
		if (diagramDao.getDiagramByName(nameDiagramNew, project.getProjectid()) != null) {
			return "";
		}
		
		Diagram diagram = diagramDao.getDiagramByName(nameDiagramOld, project.getProjectid());
		
		diagram.setNamediagram(nameDiagramNew);
		diagramDao.update(diagram);
		
		return "1";
	}
	
	@RequestMapping(value = "/diagram/setpathdiagram", produces="text/plain; charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String setPathDiagram(@RequestParam(value="nameProject") String nameProject,
			@RequestParam(value="nameDiagram") String nameDiagram,
			@RequestParam(value="path") String path,
			@RequestParam(value="image") String image,
			Principal principal) {
		
		// Lay du an hien tai
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		Diagram diagram = diagramDao.getDiagramByName(nameDiagram, project.getProjectid());
		diagram.setPath(path);
		diagram.setImage(image);
		diagramDao.update(diagram);
		
		return "1";
	}
	
	@RequestMapping(value = "/diagram/createactor", method = RequestMethod.POST, produces="text/plain; charset=utf-8")
	public @ResponseBody String createActor (@RequestParam(value="nameProject") String nameProject, 
				@RequestParam(value="nameDiagram") String nameDiagram,
				@RequestParam(value="nameActor") String nameActor,
				Principal principal) {
		
		// Lay du an hien tai
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		Actor actor = new Actor();
		actor.setNameofactor(nameActor);
		actor.setProject(project);
		actor.setLoaiactor(loaiactorDao.getLoaiactorById(1));
		actorDao.add(actor);
		
		Diagram diagram = diagramDao.getDiagramByName(nameDiagram, project.getProjectid());
		
		DiagramActorId dai = new DiagramActorId(); 
		dai.setActorid(actor.getActorid());
		dai.setDiagramid(diagram.getDiagramid());
		
		DiagramActor da = new DiagramActor();
		da.setActor(actor);
		da.setDiagram(diagram);
		da.setId(dai);
		diagramActorDao.save(da);

		return actor.getActorid()+ "";
	}
	
	@RequestMapping(value = "/diagram/savediagramactor", method = RequestMethod.POST, produces="text/plain; charset=utf-8")
	public @ResponseBody String saveDiagramActor (@RequestParam(value="nameProject") String nameProject, 
				@RequestParam(value="nameDiagram") String nameDiagram,
				@RequestParam(value="idActor") String idActor,
				Principal principal) {
		
		// Lay du an hien tai
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		
		Actor actor = actorDao.getActorByID(Integer.parseInt(idActor));
		
		Diagram diagram = diagramDao.getDiagramByName(nameDiagram, project.getProjectid());
		if (diagramActorDao.getDiagramActorByID(diagram.getDiagramid(), actor.getActorid()) == null) {
			DiagramActorId dai = new DiagramActorId(); 
			dai.setActorid(actor.getActorid());
			dai.setDiagramid(diagram.getDiagramid());
			
			DiagramActor da = new DiagramActor();
			da.setActor(actor);
			da.setDiagram(diagram);
			da.setId(dai);
			diagramActorDao.save(da);
		}
		return "";
	}
	
	@RequestMapping(value = "/diagram/savediagramusecase", method = RequestMethod.POST, produces="text/plain; charset=utf-8")
	public @ResponseBody String saveDiagramUsecase (@RequestParam(value="nameProject") String nameProject, 
				@RequestParam(value="nameDiagram") String nameDiagram,
				@RequestParam(value="idUsecase") String idUsecase,
				Principal principal) {
		
		// Lay du an hien tai
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		
		Usecase usecase = usecaseDao.getUsecaseByID(Integer.parseInt(idUsecase));
		
		Diagram diagram = diagramDao.getDiagramByName(nameDiagram, project.getProjectid());
		
		if (diagramUsecaseDao.getDiagramUsecaseByID(diagram.getDiagramid(), usecase.getUsecaseid()) == null) {
			DiagramUsecaseId dui = new DiagramUsecaseId(); 
			dui.setUsecaseid(usecase.getUsecaseid());
			dui.setDiagramid(diagram.getDiagramid());
			
			DiagramUsecase du = new DiagramUsecase();
			du.setUsecase(usecase);
			du.setDiagram(diagram);
			du.setId(dui);
			diagramUsecaseDao.save(du);
		}

		return "";
	}
	
	@RequestMapping(value = "/diagram/createusecase", method = RequestMethod.POST)
	public @ResponseBody String createUsecase (@RequestParam(value="nameProject") String nameProject, 
				@RequestParam(value="nameDiagram") String nameDiagram,
				@RequestParam(value="nameUsecase") String nameUsecase,
				Principal principal) {
		
		// Lay du an hien tai
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		
		Usecase usecase = new Usecase();
		usecase.setBmt(bmtDao.getBmtById(1));
		usecase.setTinhtien(true);
		usecase.setNameofuc(nameUsecase);
		usecase.setProject(project);
		if (!nhomucDao.hasNhomuc(nameDiagram, project.getProjectid())) {
			Nhomuc nhomuc = new Nhomuc();
			nhomuc.setProject(project);
			nhomuc.setTennhom(nameDiagram);
			nhomucDao.add(nhomuc);
			usecase.setNhomuc(nhomuc);
		} else {
			usecase.setNhomuc(nhomucDao.getNhomucByName(nameDiagram, project.getProjectid()));
		}
		usecaseDao.add(usecase);
		Diagram diagram = diagramDao.getDiagramByName(nameDiagram, project.getProjectid());
		
		DiagramUsecaseId dui = new DiagramUsecaseId(); 
		dui.setUsecaseid(usecase.getUsecaseid());
		dui.setDiagramid(diagram.getDiagramid());
		
		DiagramUsecase du = new DiagramUsecase();
		du.setUsecase(usecase);
		du.setDiagram(diagram);
		du.setId(dui);
		diagramUsecaseDao.save(du);

		return "" + usecase.getUsecaseid();
	}
	@RequestMapping(value = "/diagram/saveinfoactor", method = RequestMethod.POST)
	public @ResponseBody String saveInfoActor (@RequestParam(value="nameProject") String nameProject, 
				@RequestParam(value="id") String id,
				@RequestParam(value="description") String description,
				@RequestParam(value="level") String level,
				@RequestParam(value="role") String role,
				@RequestParam(value="name") String name,
				Principal principal) {
		
		// Lay du an hien tai
		Actor actor = actorDao.getActorByID(Integer.parseInt(id));
		actor.setNameofactor(name);
		actor.setMotaactor(description);
		actor.setLoaiactor(loaiactorDao.getLoaiactorById(Integer.parseInt(level)));
		if (!role.equals("")) {
			String[] roles = role.split("/");
			for (int j = 0; j < roles.length; j++) {
				if (roles[j] != "") {
					String[] attrRole = roles[j].split("_");
					
					Usecase uc = usecaseDao.getUsecaseByID(Integer.parseInt(attrRole[1]));
					Phanloai pl = phanLoaiDao.getPhanLoaibyUsecaseActor(uc.getUsecaseid(), actor.getActorid());
					pl.setVaitro(Integer.parseInt(attrRole[0]));
					phanLoaiDao.update(pl);
				}
			}
		}
		actorDao.update(actor);
		return "";
	}
	@RequestMapping(value = "/diagram/saveinfousecase", method = RequestMethod.POST)
	public @ResponseBody String saveInfoUsecase (@RequestParam(value="nameProject") String nameProject, 
				@RequestParam(value="id") String id,
				@RequestParam(value="description") String description,
				@RequestParam(value="level") String level,
				@RequestParam(value="name") String name,
				@RequestParam(value="payMoney") String payMoney,
				@RequestParam(value="group") String group,
				Principal principal) {
		
		// Lay du an hien tai
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		Usecase uc = usecaseDao.getUsecaseByID(Integer.parseInt(id));
		uc.setNameofuc(name);
		uc.setMotauc(description);
		uc.setBmt(btmDao.getBmtById(Integer.parseInt(level)));
		if (payMoney.contains("true")) {
			uc.setTinhtien(true);
		} else {
			uc.setTinhtien(false);
		}
		if (!group.equals("")) {
			if (!nhomucDao.hasNhomuc(group, project.getProjectid())) {
				Nhomuc nhomuc = new Nhomuc();
				nhomuc.setProject(project);
				nhomuc.setTennhom(group);
				nhomucDao.add(nhomuc);
				uc.setNhomuc(nhomuc);
			} else {
				uc.setNhomuc(nhomucDao.getNhomucByName(group, project.getProjectid()));
			}
		}
		usecaseDao.update(uc);
		return "";
	}
	
	private void deleteNhomucNoChild(int projectId) {
		List<Nhomuc> nhomucs = nhomucDao.getNhomucByProject(projectId);
		if (nhomucs.size() > 0) {
			for (int i = 0; i < nhomucs.size(); i++) {
				if (nhomucs.get(i).getUsecases().size() < 1) {
					nhomucDao.delete(nhomucs.get(i));
				}
			}
 		}
	}
	
	@RequestMapping(value = "/diagram/savediagram", method = RequestMethod.POST, produces="text/plain; charset=utf-8")
	public @ResponseBody String addUsecase (@RequestParam(value="nameProject") String nameProject,
				@RequestParam(value="path") String path,
				@RequestParam(value="image") String image,
				@RequestParam(value="nameDiagram") String nameDiagram,
				@RequestParam(value="actorArray") List<String> actorArray,
				@RequestParam(value="ucArray") List<String> ucArray,
				Principal principal) {
		
		// Lay du an hien tai
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		
		// Update element on diagram
		for (int i = 0; i < ucArray.size(); i++) {
			String[] ucAttr = ucArray.get(i).split("=");
			Usecase uc = usecaseDao.getUsecaseByName(ucAttr[0], project.getProjectid());
			if (uc != null && uc.getUsecaseid() != Integer.parseInt(ucAttr[1])) {
				return "";
			}
			uc = usecaseDao.getUsecaseByID(Integer.parseInt(ucAttr[1]));
			uc.setNameofuc(ucAttr[0]);
			usecaseDao.update(uc);
		}
		
		// Update element on diagram
		for (int i = 0; i < actorArray.size(); i++) {
			String[] actorAttr = actorArray.get(i).split("=");
			Actor actor = actorDao.getActorByName(actorAttr[0], project.getProjectid());
			if (actor != null && actor.getActorid() != Integer.parseInt(actorAttr[1])) {
				return "";
			}
			actor = actorDao.getActorByID(Integer.parseInt(actorAttr[1]));
			actor.setNameofactor(actorAttr[0]);
			actorDao.update(actor);
		}
	
		Diagram diagram = diagramDao.getDiagramByName(nameDiagram, project.getProjectid());
		diagram.setPath(path);
		diagram.setImage(image);
		diagramDao.update(diagram);
		
		return "1";
	}
	
	@RequestMapping(value = "/diagram/setroleactor", method = RequestMethod.POST, produces="text/plain; charset=utf-8")
	public @ResponseBody String setRoleActor(@RequestParam(value="idActor") String idActor, @RequestParam(value="idUsecase") String idUsecase) {
		
		Actor actor = actorDao.getActorByID(Integer.parseInt(idActor));
		Usecase uc = usecaseDao.getUsecaseByID(Integer.parseInt(idUsecase));
		
		if (phanLoaiDao.getPhanLoaibyUsecaseActor(uc.getUsecaseid(), actor.getActorid()) == null) {
			PhanloaiId plid = new PhanloaiId();
			plid.setActorid(actor.getActorid());
			plid.setUsecaseid(uc.getUsecaseid());
			
			Phanloai pl = new Phanloai();
			pl.setId(plid);
			pl.setActor(actor);
			pl.setUsecase(uc);
			pl.setVaitro(1);
			phanLoaiDao.add(pl);
		}
		return "";
	}
	
	@RequestMapping(value = "/diagram/removeroleactor", method = RequestMethod.POST)
	public @ResponseBody String removeRoleActor(@RequestParam(value="idActor") String idActor,
			@RequestParam(value="idUsecase") String idUsecase,
			Principal principal) {
		Actor actor = actorDao.getActorByID(Integer.parseInt(idActor));
		Usecase uc = usecaseDao.getUsecaseByID(Integer.parseInt(idUsecase));
		if (actor != null && uc != null) {
			phanLoaiDao.getPhanLoaibyUsecaseActor(uc.getUsecaseid(), actor.getActorid());
			if (phanLoaiDao.getPhanLoaibyUsecaseActor(uc.getUsecaseid(), actor.getActorid()) != null) {
				phanLoaiDao.deleteByUsecaseActor(uc.getUsecaseid(), actor.getActorid());
			}
		}
		
		return "";
	}
	
	@RequestMapping(value = "/diagram/viewlistdiagram", produces="text/plain; charset=utf-8")
	public @ResponseBody String viewListDiagram(@RequestParam(value="nameProject") String nameProject, Principal principal) {
		// Lay du an hien tai
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		List<Diagram> diagrams = diagramDao.getDiagramByProject(project.getProjectid());
		String result = "{\"diagram\":[";
		if (diagrams.size() > 0) {
			for (int i = 0; i < diagrams.size() - 1; i++) {
				if (diagrams.get(i).getNamediagram() != null) {
					result += "{ \"name\" : \"" + diagrams.get(i).getNamediagram() + "\",";
				} else {
					result += "{ \"name\" : \"\",";
				}
				if (diagrams.get(i).getImage() != null) {
					result += "\"image\" : \"" + diagrams.get(i).getImage() + "\"},";
				} else {
					result += "\"image\" : \"\"},";
				}
			}
			if (diagrams.get(diagrams.size() - 1).getNamediagram() != null) {
				result += "{ \"name\" : \"" + diagrams.get(diagrams.size() - 1).getNamediagram() + "\",";
			} else {
				result += "{ \"name\" : \"\",";
			}
			if (diagrams.get(diagrams.size() - 1).getImage() != null) {
				result += "\"image\" : \"" + diagrams.get(diagrams.size() - 1).getImage() + "\"}";
			} else {
				result += "\"image\" : \"\"}";
			}	
		}
		result += "]}";  
		return result;
	}
	
	@RequestMapping(value = "/diagram/deletediagram", produces="text/plain; charset=utf-8")
	public @ResponseBody String deleteUI(@RequestParam(value="nameProject") String nameProject,
			@RequestParam(value="nameDiagram") String nameDiagram, Principal principal) {
	
		// Lay du an hien tai
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		Diagram diagram = diagramDao.getDiagramByName(nameDiagram, project.getProjectid());
		diagramDao.delete(diagram);
		return "";
	}
	
	@RequestMapping(value = "/diagram/editdiagram", produces="text/plain; charset=utf-8")
	public @ResponseBody String editDiagram(@RequestParam(value="nameProject") String nameProject,
			@RequestParam(value="nameDiagram") String nameDiagram, Principal principal) {
	
		// Lay du an hien tai
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		Diagram diagram = diagramDao.getDiagramByName(nameDiagram, project.getProjectid());
		return diagram.getPath();
	}
	
	@RequestMapping(value = "/diagram/deleteobject", produces="text/plain; charset=utf-8")
	public @ResponseBody String editDiagramNew(@RequestParam(value="nameProject") String nameProject,
			@RequestParam(value="type") String type, @RequestParam(value="id") String id, Principal principal) {
		String rootPath = System.getProperty("catalina.home");
		String locationSave = rootPath + File.separator + "fileUpload" + File.separator;
		// Lay du an hien tai
		if (type.equals("uml.Actor")) {
			actorDao.delete(actorDao.getActorByID(Integer.parseInt(id)));
		} else if (type.equals("uml.Usecase")) {
			Usecase usecase = usecaseDao.getUsecaseByID(Integer.parseInt(id));
			List<FileUC> listFile = usecase.getFiles();
			for(FileUC file : listFile){
				File Filedelete = new File(locationSave+file.getLink());
				if(Filedelete.exists()){
					Filedelete.delete();
					fileUCDao.delete(file);
				}
			}
			usecaseDao.delete(usecase);
		}
		return "";
	}
	
	@RequestMapping(value = "/diagram/scoreactor", produces="text/plain; charset=utf-8")
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
	
	@RequestMapping(value = "/diagram/scoreusecase", produces="text/plain; charset=utf-8")
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
			
			if (type.equals("B ") && u.getTinhtien() == true) {
				typeB ++;
				if (level.equals(" Đơn giản") && u.getTinhtien() == true) {
					typeBSimple ++;
				} else if (level.equals(" Trung bình") && u.getTinhtien() == true){
					typeBAverage ++;
				} else if (level.equals(" Phức tạp") && u.getTinhtien() == true) {
					typeBComplex ++;
				}
			} else if (type.equals("M ") && u.getTinhtien() == true){
				typeM ++;
				if (level.equals(" Đơn giản") && u.getTinhtien() == true) {
					typeMSimple ++;
				} else if (level.equals(" Trung bình") && u.getTinhtien() == true){
					typeMAverage ++;
				} else if (level.equals(" Phức tạp") && u.getTinhtien() == true) {
					typeMComplex ++;
				}
			} else if (type.equals("T ") && u.getTinhtien() == true) {
				typeT ++;
				if (level.equals(" Đơn giản") && u.getTinhtien() == true) {
					typeTSimple ++;
				} else if (level.equals(" Trung bình") && u.getTinhtien() == true){
					typeTAverage ++;
				} else if (level.equals(" Phức tạp") && u.getTinhtien() == true) {
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
