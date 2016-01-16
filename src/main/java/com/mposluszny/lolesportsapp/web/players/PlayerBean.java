package com.mposluszny.lolesportsapp.web.players;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mposluszny.loldbmongo.domain.Player;
import com.mposluszny.loldbmongo.service.PlayerService;
import com.mposluszny.loldbmongo.service.impl.PlayerServiceImpl;

public class PlayerBean implements Serializable {

	/**
	 * Bean for 'players' section.
	 * Contains a Player list
	 */
	
	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/beans.xml");
	PlayerService playerService = applicationContext.getBean(PlayerServiceImpl.class);
	
	private static final long serialVersionUID = 1551523865258549328L;

	public List<Player> getPlayers() {
		return playerService.getPlayers();
	}
	
}
