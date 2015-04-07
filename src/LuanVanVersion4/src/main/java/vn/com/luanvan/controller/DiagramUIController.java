package vn.com.luanvan.controller;

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

import vn.com.luanvan.dao.NhomucDao;
import vn.com.luanvan.dao.ProjectDao;
import vn.com.luanvan.dao.UIDao;
import vn.com.luanvan.dao.UIUsecaseDao;
import vn.com.luanvan.dao.UsecaseDao;
import vn.com.luanvan.dao.UserDao;
import vn.com.luanvan.model.Nhomuc;
import vn.com.luanvan.model.Project;
import vn.com.luanvan.model.UI;
import vn.com.luanvan.model.UIUsecase;
import vn.com.luanvan.model.UIUsecaseId;
import vn.com.luanvan.model.Usecase;
import vn.com.luanvan.model.User;

@Controller
public class DiagramUIController {
	
	@Autowired
	ProjectDao projectDao;
	@Autowired
	UIDao uiDao;
	@Autowired
	UsecaseDao usecaseDao;
	@Autowired
	UIUsecaseDao uiUsecaseDao;
	@Autowired
	UserDao userDao;
	@Autowired
	NhomucDao nhomucDao;
	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "/diagram/newdiagramui")
	public String newDiagram(Principal principal, Model model) {
		String projectName = (String) request.getParameter("nameProject");
		String username = principal.getName();
		Project project = projectDao.findProjectByName(username, projectName);
		User user = userDao.findUserbyUserName(username);
		model.addAttribute("user", user);
		model.addAttribute("project", project);
		model.addAttribute("nhomUC", nhomucDao.getNhomucByProject(project.getProjectid()));
		//model.addAttribute("usecases", usecaseDao.getUsecaseByProject(project.getProjectid()));
		return "newui";
	}
	
	@RequestMapping(value = "/diagramui/viewdiagramui")
	public String viewDiagram(Principal principal, Model model) {
		String projectName = (String) request.getParameter("nameProject");
		String username = principal.getName();
		Project project = projectDao.findProjectByName(username, projectName);
		
		String nameUI = (String) request.getParameter("nameUI");
		UI ui = uiDao.getUIByName(nameUI, project.getProjectid());
		if (ui != null) {
			model.addAttribute("path", ui.getPath());
			model.addAttribute("nameUI", nameUI);
		} else {
			model.addAttribute("path", "");
			model.addAttribute("nameUI", "");
		}
		User user = userDao.findUserbyUserName(username);
		model.addAttribute("user", user);
		model.addAttribute("project", project);
		model.addAttribute("usecases", usecaseDao.getUsecaseByProject(project.getProjectid()));
		return "newui";
	}
	
	@RequestMapping(value = "/diagramui/assignuiusecase", method = RequestMethod.POST, produces="text/plain; charset=utf-8")
	public @ResponseBody String assignUIUsecase(@RequestParam(value="nameProject") String nameProject,
			@RequestParam(value="usecaseIDs") String usecaseIDs, @RequestParam(value="nameui") String nameui, Principal principal) {
		
			// Lay du an hien tai
			Project project = projectDao.findProjectByName(principal.getName(), nameProject);
			UI ui = uiDao.getUIByName(nameui, project.getProjectid());
			// Delete all UIUsecase
			uiUsecaseDao.deleteAllByID(ui.getUiid());
			String[] idArray = usecaseIDs.split("_");
			for (int i = 0; i < idArray.length; i++) {
				Usecase usecase = usecaseDao.getUsecaseByID(Integer.parseInt(idArray[i]));
				UIUsecaseId uiUsecaseId = new UIUsecaseId();
				uiUsecaseId.setUiid(ui.getUiid());
				uiUsecaseId.setUsecaseid(usecase.getUsecaseid());
				UIUsecase uiUsecase = new UIUsecase();
				uiUsecase.setId(uiUsecaseId);
				uiUsecase.setUi(ui);
				uiUsecase.setUsecase(usecase);
				uiUsecaseDao.save(uiUsecase);
			}
		return "";
	}
	
	@RequestMapping(value = "/diagramui/savediagramui", produces="text/plain; charset=utf-8")
	public @ResponseBody String saveDiagramUI(@RequestParam(value="nameProject") String nameProject,
			@RequestParam(value="path") String path, @RequestParam(value="nameui") String nameui,
			@RequestParam(value="image") String image, Principal principal) {
			
			// Lay du an hien tai
			Project project = projectDao.findProjectByName(principal.getName(), nameProject);
			
			UI uiTest = uiDao.getUIByName(nameui, project.getProjectid());
			if (uiTest != null) {
				uiTest.setImage(image);
				uiTest.setPath(path);
				uiDao.update(uiTest);
			} else {
				UI ui = new UI();
				ui.setImage(image);
				ui.setNameui(nameui);
				ui.setPath(path);
				ui.setProject(project);
				uiDao.add(ui);
			}
		return "";
	}
	
	@RequestMapping(value = "/diagramui/viewlistui", produces="text/plain; charset=utf-8")
	public @ResponseBody String viewListUI(@RequestParam(value="nameProject") String nameProject, Principal principal) {
		// Lay du an hien tai
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		List<UI> uis = uiDao.getUIByProject(project.getProjectid());
		String result = "{\"ui\":[";
		if (uis.size() > 0) {
			for (int i = 0; i < uis.size() - 1; i++) {
				if (uis.get(i).getNameui() != null) {
					result += "{ \"name\" : \"" + uis.get(i).getNameui() + "\",";
				} else {
					result += "{ \"name\" : \"\",";
				}
				if (uis.get(i).getImage() != null) {
					result += "\"image\" : \"" + uis.get(i).getImage() + "\"},";
				} else {
					result += "\"image\" : \"\"},";
				}
			}
			if (uis.get(uis.size() - 1).getNameui() != null) {
				result += "{ \"name\" : \"" + uis.get(uis.size() - 1).getNameui() + "\",";
			} else {
				result += "{ \"name\" : \"\",";
			}
			if (uis.get(uis.size() - 1).getImage() != null) {
				result += "\"image\" : \"" + uis.get(uis.size() - 1).getImage() + "\"}";
			} else {
				result += "\"image\" : \"\"}";
			}
		}
		result += "]}";
		return result;
	}
	
	@RequestMapping(value = "/diagramui/deleteui", produces="text/plain; charset=utf-8")
	public @ResponseBody String deleteUI(@RequestParam(value="nameProject") String nameProject,
			@RequestParam(value="nameui") String nameui, Principal principal) {
	
		// Lay du an hien tai
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		UI ui = uiDao.getUIByName(nameui, project.getProjectid());
		uiDao.delete(ui);
		return "";
	}
	
	@RequestMapping(value = "/diagramui/loadui", produces="text/plain; charset=utf-8")
	public @ResponseBody String loadUI(@RequestParam(value="nameProject") String nameProject,
			@RequestParam(value="nameui") String nameui, Principal principal) {
	
		// Lay du an hien tai
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		UI ui = uiDao.getUIByName(nameui, project.getProjectid());
		return ui.getPath();
	}
	
	@RequestMapping(value = "/diagramui/renameui", produces="text/plain; charset=utf-8")
	public @ResponseBody String renameUI(@RequestParam(value="nameProject") String nameProject,
			@RequestParam(value="nameuiOld") String nameuiOld, @RequestParam(value="nameuiNew") String nameuiNew, Principal principal) {
	
		// Lay du an hien tai
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		UI ui = uiDao.getUIByName(nameuiOld, project.getProjectid());
		ui.setNameui(nameuiNew);
		uiDao.update(ui);
		return "";
	}
	
	@RequestMapping(value = "/diagramui/checknameui", produces="text/plain; charset=utf-8")
	public @ResponseBody String checkNameUI(@RequestParam(value="nameProject") String nameProject,
			@RequestParam(value="nameui") String nameui, Principal principal) {
	
		// Lay du an hien tai
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		List<UI> uis = uiDao.getUIByProject(project.getProjectid());
		UI ui = uiDao.getUIByName(nameui, project.getProjectid());
		if (ui != null) {
			for (UI u : uis) {
				if (u.getUiid() == ui.getUiid()) {
					return "true";
				}
			}
		}
		return "false";
	}
	
	@RequestMapping(value = "/diagramui/loaduiusecase", produces="text/plain; charset=utf-8")
	public @ResponseBody String loadUIUsecase(@RequestParam(value="nameProject") String nameProject,
			@RequestParam(value="nameui") String nameui, Principal principal) {
	
		// Lay du an hien tai
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		int projectid = project.getProjectid();
		
		UI ui = uiDao.getUIByName(nameui, projectid);
		int uiid = ui.getUiid();
		
		List<Nhomuc> nhomUCs = nhomucDao.getNhomucByProject(projectid);
		int sizeNhomUCs = nhomUCs.size();
		
		String result = "{\"nhomucs\":[";
		if (sizeNhomUCs > 0) {
			for (int j = 0; j < sizeNhomUCs; j++) {
				Nhomuc nhomUc = nhomUCs.get(j);
				int nhomUcId = nhomUc.getNhomucid();
				result += "{\"name\": \"" + nhomUc.getTennhom() + "\", \"usecases\":[";
				List<Usecase> usecases = usecaseDao.getUsecaseByProjectNhomUC(projectid, nhomUcId);
				int sizeUsecases = usecases.size();
				if (sizeUsecases > 0) {
					for (int i = 0; i < sizeUsecases; i++) {
						Usecase uc = usecases.get(i);
						int ucid = uc.getUsecaseid();
						if (uc.getNhomuc().getNhomucid() == nhomUcId) {
							if (uc.getNameofuc() != null) {
								result += "{ \"name\" : \"" + uc.getNameofuc() + "\",";
							} else {
								result += "{ \"name\" : \"\",";
							}
							result += "\"id\" : \"" + ucid + "\",";
							if (uiUsecaseDao.hasUsecaseIDOfUIID(ucid, uiid)) {
								if (i != sizeUsecases - 1) {
									result += "\"checked\" : \"1\"},";
								} else {
									result += "\"checked\" : \"1\"}";
								}
							} else {
								if (i != sizeUsecases - 1) {
									result += "\"checked\" : \"0\"},";
								} else {
									result += "\"checked\" : \"0\"}";
								}
							}
						}
					}
				}
				if (sizeNhomUCs - 1 != j) {
					result += "]},";
				} else {
					result += "]}";
				}
			}
		}
		result += "]}";
		return result;
	}
}
