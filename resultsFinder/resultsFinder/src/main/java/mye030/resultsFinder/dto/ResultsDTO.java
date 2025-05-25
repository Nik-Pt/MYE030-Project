package mye030.resultsFinder.dto;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class ResultsDTO {
	private Date date;
	private String home_team;
	private String away_team;
	private int home_score;
	private int away_score;
	private String tournament;
	private String city;
	private String country;
	private int neutral;
	
	public ResultsDTO(Date date, String home_team, String away_team, int home_score, int away_score, String tournament,
			String city, String country, int neutral) {
		super();
		this.date = date;
		this.home_team = home_team;
		this.away_team = away_team;
		this.home_score = home_score;
		this.away_score = away_score;
		this.tournament = tournament;
		this.city = city;
		this.country = country;
		this.neutral = neutral;
	}
	
	public ResultsDTO() {
		super();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getHome_team() {
		return home_team;
	}

	public void setHome_team(String home_team) {
		this.home_team = home_team;
	}

	public String getAway_team() {
		return away_team;
	}

	public void setAway_team(String away_team) {
		this.away_team = away_team;
	}

	public int getHome_score() {
		return home_score;
	}

	public void setHome_score(int home_score) {
		this.home_score = home_score;
	}

	public int getAway_score() {
		return away_score;
	}

	public void setAway_score(int away_score) {
		this.away_score = away_score;
	}

	public String getTournament() {
		return tournament;
	}

	public void setTournament(String tournament) {
		this.tournament = tournament;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getNeutral() {
		return neutral;
	}

	public void setNeutral(int neutral) {
		this.neutral = neutral;
	}
}
