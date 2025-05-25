package mye030.resultsFinder.datamodel;

import java.sql.Date;

import org.springframework.data.annotation.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Immutable
@Table(name = "scorers")
@IdClass(ScorerKey.class)
public class Scorer {
	
	@Id
	@Column(name = "date")
	private Date date;
	
	@Id
	@Column(name = "home_team")
	private int home_team;
	
	@Id
	@Column(name = "away_team")
	private int away_team;
	
	@Column(name = "team")
	private String team;
	
	@Id
	@Column(name = "scorer")
	private String scorer;
	
	@Id
	@Column(name = "minute")
	private double minute;
	
	@Column(name = "own_goal")
	private boolean own_goal;
	
	@Column(name = "penalty")
	private boolean penalty;

	public Scorer(Date date, int home_team, int away_team, String team, String scorer, double minute, boolean own_goal,
			boolean penalty) {
		super();
		this.date = date;
		this.home_team = home_team;
		this.away_team = away_team;
		this.team = team;
		this.scorer = scorer;
		this.minute = minute;
		this.own_goal = own_goal;
		this.penalty = penalty;
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

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
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

	public boolean isOwn_goal() {
		return own_goal;
	}

	public void setOwn_goal(boolean own_goal) {
		this.own_goal = own_goal;
	}

	public boolean isPenalty() {
		return penalty;
	}

	public void setPenalty(boolean penalty) {
		this.penalty = penalty;
	}

	@Override
	public String toString() {
		return "Scorer [date=" + date + ", home_team=" + home_team + ", away_team=" + away_team + ", team=" + team
				+ ", scorer=" + scorer + ", minute=" + minute + ", own_goal=" + own_goal + ", penalty=" + penalty + "]";
	}
}