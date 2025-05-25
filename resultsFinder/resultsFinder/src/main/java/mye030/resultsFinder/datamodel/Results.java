package mye030.resultsFinder.datamodel;

import java.sql.Date;

import org.springframework.data.annotation.Immutable;
import jakarta.persistence.*;

@Entity
@Immutable
@Table(name = "results")
@IdClass(ResultsKey.class)
public class Results {
	
	@Id
	@Column(name = "date")
	private Date date;
	
	@Id
	@Column(name = "home_team")
	private int home_team;
	
	@Id
	@Column(name = "away_team")
	private int away_team;
	
	@Column(name = "home_score")
	private int home_score;
	
	@Column(name = "away_score")
	private int away_score;
	
	@Id
	@Column(name = "tournament")
	private String tournament;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "neutral")
	private int neutral;

	public Results() {
		super();
	}

	public Results(Date date, int home_team, int away_team, int home_score, int away_score, String tournament,
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

	@Override
	public String toString() {
		return "Results [date=" + date + ", home_team=" + home_team + ", away_team=" + away_team + ", home_score="
				+ home_score + ", away_score=" + away_score + ", tournament=" + tournament + ", city=" + city
				+ ", country=" + country + ", neutral=" + neutral + "]";
	}
	
}