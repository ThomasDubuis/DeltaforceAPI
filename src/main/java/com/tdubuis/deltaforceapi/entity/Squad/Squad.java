package com.tdubuis.deltaforceapi.entity.Squad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tdubuis.deltaforceapi.entity.Player;
import com.tdubuis.deltaforceapi.exception.ApiException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Squad
{
	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn()
	private Player player1;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn()
	private Player player2;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn()
	private Player player3;

	@Column(nullable = false, unique = true)
	@JsonIgnore
	private UUID joiningKey;

	@OneToMany(mappedBy = "squad", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SquadRotation> rotations = new ArrayList<>();

	@PrePersist
	public void generateJoiningKey() {
		if (joiningKey == null) {
			joiningKey = UUID.randomUUID();
		}
	}

	public void addPlayer(Player player) {

		if (getPlayer2() == null) {
			setPlayer2(player);
		} else if (getPlayer3() == null) {
			setPlayer3(player);
		} else {
			throw new ApiException(HttpStatus.CONFLICT, "Squad is already full");
		}

		SquadRotation r = new SquadRotation();
		r.setId(new SquadRotationId(this.id, player.getId()));
		r.setSquad(this);
		r.setPlayer(player);
		r.setRotation(rotations.size() + 1);

		rotations.add(r);
	}

	public void movePlayerToEnd(UUID playerId) {

		List<SquadRotation> rotations = this.getRotations();

		if (rotations == null || rotations.isEmpty()) return;

		SquadRotation target = rotations.stream()
				.filter(r -> r.getPlayer().getId().equals(playerId))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("ERROR Player not in squad")); //Here not apiException because never should happen

		int oldPos = target.getRotation();

		rotations.forEach(r -> {
			if (r.getRotation() > oldPos) {
				r.setRotation(r.getRotation() - 1);
			}
		});

		target.setRotation(rotations.size());
	}
}
