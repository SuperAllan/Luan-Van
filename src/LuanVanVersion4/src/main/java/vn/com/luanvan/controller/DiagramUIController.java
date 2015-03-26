package vn.com.luanvan.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.com.luanvan.dao.ProjectDao;
import vn.com.luanvan.dao.UIDao;
import vn.com.luanvan.model.Project;
import vn.com.luanvan.model.UI;

@Controller
public class DiagramUIController {
	
	@Autowired
	ProjectDao projectDao;
	@Autowired
	UIDao uiDao;

	@RequestMapping(value = "/diagramui/savediagramui")
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
	
	@RequestMapping(value = "/diagramui/viewlistui")
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
	
	@RequestMapping(value = "/diagramui/deleteui")
	public @ResponseBody String deleteUI(@RequestParam(value="nameProject") String nameProject,
			@RequestParam(value="nameui") String nameui, Principal principal) {
	
		// Lay du an hien tai
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		UI ui = uiDao.getUIByName(nameui, project.getProjectid());
		uiDao.delete(ui);
		return "";
	}
	
	@RequestMapping(value = "/diagramui/loadui")
	public @ResponseBody String loadUI(@RequestParam(value="nameProject") String nameProject,
			@RequestParam(value="nameui") String nameui, Principal principal) {
	
		// Lay du an hien tai
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		UI ui = uiDao.getUIByName(nameui, project.getProjectid());
		return ui.getPath();
	}
	
	@RequestMapping(value = "/diagramui/renameui")
	public @ResponseBody String renameUI(@RequestParam(value="nameProject") String nameProject,
			@RequestParam(value="nameuiOld") String nameuiOld, @RequestParam(value="nameuiNew") String nameuiNew, Principal principal) {
	
		// Lay du an hien tai
		Project project = projectDao.findProjectByName(principal.getName(), nameProject);
		UI ui = uiDao.getUIByName(nameuiOld, project.getProjectid());
		ui.setNameui(nameuiNew);
		uiDao.update(ui);
		return "";
	}
	
	@RequestMapping(value = "/diagramui/checknameui")
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
}
