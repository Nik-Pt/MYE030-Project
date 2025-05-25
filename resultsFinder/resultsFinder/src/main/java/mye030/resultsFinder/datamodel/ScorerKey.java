package mye030.resultsFinder.datamodel;

import java.sql.Date;

import jakarta.persistence.Embeddable;

@Embeddable
public class ScorerKey {
	
	private Date date;
	private int home_team;
	private int away_team;
	private String scorer;
	private double minute;
	
	public ScorerKey() {
		super();
	}

	public ScorerKey(Date date, int home_team, int away_team, String scorer, double minute) {
		super();
		this.date = date;
		this.home_team = home_team;
		this.away_team = away_team;
		this.scorer = scorer;
		this.minute = minute;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getHome_team() {
		return home_team;
	}

	public void setHome_team(int home_team) {
		this.home_team = home_team;
	}

	public int getAway_team() {
		return away_team;
	}

	public void setAway_team(int away_team) {
		this.away_team = away_team;
	}

	public String getScorer() {
		return scorer;
	}

	public void setScorer(String scorer) {
		this.scorer = scorer;
	}

	public double getMinute() {
		return minute;
	}

	public void setMinute(double minute) {
		this.minute = minute;
	}
	
}