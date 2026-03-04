package com.tdubuis.deltaforceapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tdubuis.deltaforceapi.entity.Player;
import com.tdubuis.deltaforceapi.repository.PlayerRepository;

@Service
public class PlayerService
{
	private final PlayerRepository playerRepository;

	public PlayerService(PlayerRepository playerRepository)
	{
		this.playerRepository = playerRepository;
	}

	public List<Player> getAllPlayers()
	{
		return playerRepository.findAll();
	}

	public List<Player> changeRotation(Long playerId)
	{
		Player target = playerRepository.findById(playerId)
				.orElseThrow(() -> new RuntimeException("Player not found"));

		int oldRotation = target.getRotation();

		// Nombre total de joueurs
		long maxRotation = playerRepository.count();

		// Décaler tous ceux après lui
		List<Player> playersAfter = playerRepository.findByRotationGreaterThan(oldRotation);

		for (Player p : playersAfter) {
			p.setRotation(p.getRotation() - 1);
		}

		// Mettre le joueur en dernier
		target.setRotation((int) maxRotation);

		// Sauvegarder
		playerRepository.saveAll(playersAfter);
		playerRepository.save(target);

		// Retourner liste ordonnée
		return playerRepository.findAllByOrderByRotationAsc();
	}
}
