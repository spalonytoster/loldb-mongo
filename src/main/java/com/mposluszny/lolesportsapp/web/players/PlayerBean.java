package com.mposluszny.lolesportsapp.web.players;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mposluszny.loldbmongo.domain.Player;
import com.mposluszny.loldbmongo.service.PlayerService;

@Component
public class PlayerBean implements Serializable {

	/**
	 * Bean for 'players' section.
	 * Contains a Player list
	 */
	
	@Autowired
	PlayerService playerService;
	
	private static final long serialVersionUID = 1551523865258549328L;

	public List<Player> getPlayers() {
		return playerService.getPlayers();
	}
	
}
