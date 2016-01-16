package com.mposluszny.lolesportsapp.web.teams;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mposluszny.loldbmongo.domain.Team;
import com.mposluszny.loldbmongo.service.TeamService;
import com.mposluszny.loldbmongo.service.impl.TeamServiceImpl;

public class TeamBean implements Serializable {

	/**
	 * Bean for 'teams' section.
	 * Contains a Player list
	 */
	private static final long serialVersionUID = 1551523865258549328L;

	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/beans.xml");
	TeamService teamService = applicationContext.getBean(TeamServiceImpl.class);
	
	private boolean readonly = true;

	public List<Team> getTeams() {
		return teamService.getTeams();
	}

	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}
}
