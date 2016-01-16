package com.mposluszny.lolesportsapp.web.teams;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mposluszny.loldbmongo.domain.Team;
import com.mposluszny.loldbmongo.service.PlayerService;
import com.mposluszny.loldbmongo.service.TeamService;
import com.mposluszny.loldbmongo.service.impl.PlayerServiceImpl;
import com.mposluszny.loldbmongo.service.impl.TeamServiceImpl;

@WebServlet(urlPatterns="/teams/team")
public class TeamServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8452048539808160419L;
	
	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/beans.xml");
	TeamService teamService = applicationContext.getBean(TeamServiceImpl.class);
	PlayerService playerService = applicationContext.getBean(PlayerServiceImpl.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ObjectId idTeam = null;
		Team team;
		boolean readonly = true;
		
		if (request.getParameter("view") != null) {
			idTeam = new ObjectId(request.getParameter("view"));
			readonly = true;
		}
		
		else if (request.getParameter("edit") != null) {
			idTeam = new ObjectId(request.getParameter("edit"));
			readonly = false;
		}
		
		else if (request.getParameter("delete") != null) {
			idTeam = new ObjectId(request.getParameter("delete"));
			team = teamService.getTeam(idTeam);
			teamService.deleteTeam(team);
			request.getRequestDispatcher("team/index.jsp").forward(request, response);
		}
		
		team = teamService.getTeam(idTeam);
		request.setAttribute("team", team);
		request.setAttribute("readonly", readonly);
		request.getRequestDispatcher("team/index.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Team team = new Team();

		if (request.getParameter("idTeam") != null) {
			team.setId(new ObjectId(request.getParameter("idTeam")));
			team.setName(request.getParameter("name"));
			team.setRegion(request.getParameter("region"));
			team.setDateOfEstablishment(request.getParameter("dateOfEstablishment"));
			teamService.updateTeam(team);
		}
		
		else if (request.getParameter("delete") != null) {
			team.setId(new ObjectId(request.getParameter("delete")));
			teamService.deleteTeam(team);
		}
		
		else if (request.getParameter("add") != null) {
			team.setName(request.getParameter("name"));
			team.setRegion(request.getParameter("region"));
			team.setDateOfEstablishment(request.getParameter("dateOfEstablishment"));
			teamService.addTeam(team);
		}
		
		else if (request.getParameter("deleteFromTeam") != null) {
			ObjectId idPlayer = new ObjectId(request.getParameter("deleteFromTeam"));
			playerService.deletePlayer(idPlayer);
		}
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
}
