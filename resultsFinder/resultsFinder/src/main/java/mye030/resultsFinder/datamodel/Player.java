package mye030.resultsFinder.datamodel;

import org.springframework.data.annotation.Immutable;

import jakarta.persistence.*;

@Entity
@Immutable
@Table(name = "final")
@IdClass(PlayerKey.class)
public class Player {

    @Id
    @Column(name = "scorer")
    private String scorer;

    @Id
    @Column(name = "ISO_Code")
    private int ISO_Code;

    @Column(name = "team")
    private String team;

    @Column(name = "starting_year")
    private Long starting_year;

    @Column(name = "ending_year")
    private Long ending_year;

    @Column(name = "total_goals")
    private Long total_goals;

    @Column(name = "max_goals_per_match")
    private Long max_goals_per_match;

    @Column(name = "goals_ratio")
    private Double goals_ratio;

	public String getScorer() {
		return scorer;
	}

	public void setScorer(String scorer) {
		this.scorer = scorer;
	}

	public int getISO_Code() {
		return ISO_Code;
	}

	public void setISO_Code(int iSO_Code) {
		ISO_Code = iSO_Code;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Long getStarting_year() {
		return starting_year;
	}

	public void setStarting_year(Long starting_year) {
		this.starting_year = starting_year;
	}

	public Long getEnding_year() {
		return ending_year;
	}

	public void setEnding_year(Long ending_year) {
		this.ending_year = ending_year;
	}

	public Long getTotal_goals() {
		return total_goals;
	}

	public void setTotal_goals(Long total_goals) {
		this.total_goals = total_goals;
	}

	public Long getMax_goals_per_match() {
		return max_goals_per_match;
	}

	public void setMax_goals_per_match(Long max_goals_per_match) {
		this.max_goals_per_match = max_goals_per_match;
	}

	public Double getGoals_ratio() {
		return goals_ratio;
	}

	public void setGoals_ratio(Double goals_ratio) {
		this.goals_ratio = goals_ratio;
	}

	@Override
	public String toString() {
		return "Player [scorer=" + scorer + ", ISO_Code=" + ISO_Code + ", team=" + team + ", starting_year="
				+ starting_year + ", ending_year=" + ending_year + ", total_goals=" + total_goals
				+ ", max_goals_per_match=" + max_goals_per_match + ", goals_ratio=" + goals_ratio + "]";
	}
}
