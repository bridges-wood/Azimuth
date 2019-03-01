package construction;

import java.io.Serializable;
import java.util.List;

class Relation {
	private Faction faction;
	private Float modifier; // In range 5 to -5. Allies to enemies.

	public Relation(Faction faction, Float modifier) {
		this.setFaction(faction);
		this.setModifier(modifier);
	}

	public Faction getFaction() {
		return faction;
	}

	public void setFaction(Faction faction) {
		this.faction = faction;
	}

	public Float getModifier() {
		return modifier;
	}

	public void setModifier(Float modifier) {
		this.modifier = modifier;
	}
}

public class Faction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4117988414284310765L;
	private String name, description, playerRank;
	private List<Relation> relations;
	private String[] ranks;
	private float stability; // Determines random coup events.

	public Faction(String name, String description, List<Relation> relations, String[] ranks, float stability) {
		this.name = name;
		this.description = description;
		this.relations = relations;
		this.ranks = ranks;
		this.stability = stability;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlayerRank() {
		return playerRank;
	}

	public void setPlayerRank(String playerRank) {
		this.playerRank = playerRank;
	}

	public List<Relation> getRelations() {
		return relations;
	}

	public void setRelations(List<Relation> relations) {
		this.relations = relations;
	}

	public String[] getRanks() {
		return ranks;
	}

	public void setRanks(String[] ranks) {
		this.ranks = ranks;
	}

	public float getStability() {
		return stability;
	}

	public void setStability(float stability) {
		this.stability = stability;
	}

}
