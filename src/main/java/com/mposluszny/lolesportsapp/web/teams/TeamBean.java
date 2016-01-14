package com.mposluszny.lolesportsapp.web.teams;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mposluszny.loldbmongo.domain.Team;
import com.mposluszny.loldbmongo.repositories.TeamRepository;
import com.mposluszny.loldbmongo.service.TeamService;

@Component
public class TeamBean implements Serializable {

	/**
	 * Bean for 'teams' section.
	 * Contains a Player list
	 */
	private static final long serialVersionUID = 1551523865258549328L;
	
	@Autowired
	@Qualifier("teamService")
	TeamService teamService;
	@Autowired
	TeamRepository teamRepository;
	
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
