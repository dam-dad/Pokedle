package teampoke.pokeapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EvolutionDetail {

	@SerializedName("gender")
	@Expose
	private Integer gender;
	@SerializedName("held_item")
	@Expose
	private Object heldItem;
	@SerializedName("item")
	@Expose
	private Object item;
	@SerializedName("known_move")
	@Expose
	private Object knownMove;
	@SerializedName("known_move_type")
	@Expose
	private Object knownMoveType;
	@SerializedName("location")
	@Expose
	private Object location;
	@SerializedName("min_affection")
	@Expose
	private Integer minAffection;
	@SerializedName("min_beauty")
	@Expose
	private Integer minBeauty;
	@SerializedName("min_happiness")
	@Expose
	private Integer minHappiness;
	@SerializedName("min_level")
	@Expose
	private Integer minLevel;
	@SerializedName("needs_overworld_rain")
	@Expose
	private Boolean needsOverworldRain;
	@SerializedName("party_species")
	@Expose
	private Object partySpecies;
	@SerializedName("party_type")
	@Expose
	private Object partyType;
	@SerializedName("relative_physical_stats")
	@Expose
	private Integer relativePhysicalStats;
	@SerializedName("time_of_day")
	@Expose
	private String timeOfDay;
	@SerializedName("trade_species")
	@Expose
	private Object tradeSpecies;
	@SerializedName("trigger")
	@Expose
	private Trigger trigger;
	@SerializedName("turn_upside_down")
	@Expose
	private Boolean turnUpsideDown;

	public Object getGender() {
	return gender;
	}

	public void setGender(Integer gender) {
	this.gender = gender;
	}

	public Object getHeldItem() {
	return heldItem;
	}

	public void setHeldItem(Object heldItem) {
	this.heldItem = heldItem;
	}

	public Object getItem() {
	return item;
	}

	public void setItem(Object item) {
	this.item = item;
	}

	public Object getKnownMove() {
	return knownMove;
	}

	public void setKnownMove(Object knownMove) {
	this.knownMove = knownMove;
	}

	public Object getKnownMoveType() {
	return knownMoveType;
	}

	public void setKnownMoveType(Object knownMoveType) {
	this.knownMoveType = knownMoveType;
	}

	public Object getLocation() {
	return location;
	}

	public void setLocation(Object location) {
	this.location = location;
	}

	public Object getMinAffection() {
	return minAffection;
	}

	public void setMinAffection(Integer minAffection) {
	this.minAffection = minAffection;
	}

	public Object getMinBeauty() {
	return minBeauty;
	}

	public void setMinBeauty(Integer minBeauty) {
	this.minBeauty = minBeauty;
	}

	public Object getMinHappiness() {
	return minHappiness;
	}

	public void setMinHappiness(Integer minHappiness) {
	this.minHappiness = minHappiness;
	}

	public Integer getMinLevel() {
	return minLevel;
	}

	public void setMinLevel(Integer minLevel) {
	this.minLevel = minLevel;
	}

	public Boolean getNeedsOverworldRain() {
	return needsOverworldRain;
	}

	public void setNeedsOverworldRain(Boolean needsOverworldRain) {
	this.needsOverworldRain = needsOverworldRain;
	}

	public Object getPartySpecies() {
	return partySpecies;
	}

	public void setPartySpecies(Object partySpecies) {
	this.partySpecies = partySpecies;
	}

	public Object getPartyType() {
	return partyType;
	}

	public void setPartyType(Object partyType) {
	this.partyType = partyType;
	}

	public Object getRelativePhysicalStats() {
	return relativePhysicalStats;
	}

	public void setRelativePhysicalStats(Integer relativePhysicalStats) {
	this.relativePhysicalStats = relativePhysicalStats;
	}

	public String getTimeOfDay() {
	return timeOfDay;
	}

	public void setTimeOfDay(String timeOfDay) {
	this.timeOfDay = timeOfDay;
	}

	public Object getTradeSpecies() {
	return tradeSpecies;
	}

	public void setTradeSpecies(Object tradeSpecies) {
	this.tradeSpecies = tradeSpecies;
	}

	public Trigger getTrigger() {
	return trigger;
	}

	public void setTrigger(Trigger trigger) {
	this.trigger = trigger;
	}

	public Boolean getTurnUpsideDown() {
	return turnUpsideDown;
	}

	public void setTurnUpsideDown(Boolean turnUpsideDown) {
	this.turnUpsideDown = turnUpsideDown;
	}

	}