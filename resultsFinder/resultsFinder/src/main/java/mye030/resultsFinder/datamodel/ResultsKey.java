package mye030.resultsFinder.datamodel;

import java.sql.Date;

import jakarta.persistence.Embeddable;

@Embeddable
public class ResultsKey {
	private Date date;
	private int home_team;
	private int away_team;
	private String tournament;
	
	public ResultsKey() {
		super();
	}

	public ResultsKey(Date date, int home_team, int away_team, String tournament) {
		super();
		this.date = date;
		this.home_team = home_team;
		this.away_team = away_team;
		this.tournament = tournament;
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

	public String getTournament() {
		return tournament;
	}

	public void setTournament(String tournament) {
		this.tournament = tournament;
	}

	@Override
	public String toString() {
		return "ResultsKey [date=" + date + ", home_team=" + home_team + ", away_team=" + away_team + ", tournament="
				+ tournament + "]";
	}
	
}