package mye030.resultsFinder.datamodel;

import org.springframework.data.annotation.Immutable;
import jakarta.persistence.*;

@Entity
@Immutable
@Table(name = "YearlyMatchStats")
@IdClass(YearlyMatchStatsKey.class)
public class YearlyMatchStats {
	
	@Id
	@Column(name = "ISO_Code")
	private int ISO_Code;
	
	@Id
	@Column(name = "year")
	private int year;
	
	@Column(name = "total_year_matches")
	private int total_year_matches;
	
	@Column(name = "total_year_draws")
	private int total_year_draws;
	
	@Column(name = "total_year_wins")
	private int total_year_wins;
	
	@Column(name = "total_year_losses")
	private int total_year_losses;
	
	@Column(name = "had_shootouts")
	private int had_shootouts;

	public YearlyMatchStats() {
		super();
	}

	public YearlyMatchStats(int iSO_Code, int year, int total_year_matches, int total_year_draws, int total_year_wins,
			int total_year_losses, int had_shootouts) {
		super();
		ISO_Code = iSO_Code;
		this.year = year;
		this.total_year_matches = total_year_matches;
		this.total_year_draws = total_year_draws;
		this.total_year_wins = total_year_wins;
		this.total_year_losses = total_year_losses;
		this.had_shootouts = had_shootouts;
	}

	public int getISO_Code() {
		return ISO_Code;
	}

	public void setISO_Code(int iSO_Code) {
		ISO_Code = iSO_Code;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getTotal_year_matches() {
		return total_year_matches;
	}

	public void setTotal_year_matches(int total_year_matches) {
		this.total_year_matches = total_year_matches;
	}

	public int getTotal_year_draws() {
		return total_year_draws;
	}

	public void setTotal_year_draws(int total_year_draws) {
		this.total_year_draws = total_year_draws;
	}

	public int getTotal_year_wins() {
		return total_year_wins;
	}

	public void setTotal_year_wins(int total_year_wins) {
		this.total_year_wins = total_year_wins;
	}

	public int getTotal_year_losses() {
		return total_year_losses;
	}

	public void setTotal_year_losses(int total_year_losses) {
		this.total_year_losses = total_year_losses;
	}

	public int getHad_shootouts() {
		return had_shootouts;
	}

	public void setHad_shootouts(int had_shootouts) {
		this.had_shootouts = had_shootouts;
	}

	@Override
	public String toString() {
		return "YearlyMatchStats [ISO_Code=" + ISO_Code + ", year=" + year + ", total_year_matches="
				+ total_year_matches + ", total_year_draws=" + total_year_draws + ", total_year_wins=" + total_year_wins
				+ ", total_year_losses=" + total_year_losses + ", had_shootouts=" + had_shootouts + "]";
	}
	
}
