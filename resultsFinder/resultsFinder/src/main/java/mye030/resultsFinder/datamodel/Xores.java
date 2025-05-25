package mye030.resultsFinder.datamodel;

import org.springframework.data.annotation.Immutable;
import jakarta.persistence.*;

@Entity
@Immutable
@Table(name = "Xores")
public class Xores {
	
	@Column(name = "Display_Name")
	private String Display_Name;
	
	@Column(name = "Official_Name")
	private String Official_Name;
	
	@Column(name = "start_year")
	private String start_year;
	
	@Column(name = "end_year")
	private String end_year;
	
	@Id
	@Column(name = "ISO_Code")
	private int ISO_Code;
	
	@Column(name = "home_wins")
	private int home_wins;
	
	@Column(name = "away_wins")
	private int away_wins;
	
	@Column(name = "total_wins")
	private int total_wins;
	
	@Column(name = "home_losses")
	private int home_losses;
	
	@Column(name = "away_losses")
	private int away_losses;
	
	@Column(name = "total_losses")
	private int total_losses;
	
	@Column(name = "home_ties")
	private int home_ties;
	
	@Column(name = "away_ties")
	private int away_ties;
	
	@Column(name = "total_ties")
	private int total_ties;
	
	@Column(name = "home_matches")
	private int home_matches;
	
	@Column(name = "away_matches")
	private int away_matches;
	
	@Column(name = "total_matches")
	private int total_matches;

	public Xores() {
		super();
	}

	public Xores(String display_Name, String official_Name, String start_year, String end_year, int iSO_Code,
			int home_wins, int away_wins, int total_wins, int home_losses, int away_losses, int total_losses,
			int home_ties, int away_ties, int total_ties, int home_matches, int away_matches, int total_matches) {
		super();
		Display_Name = display_Name;
		Official_Name = official_Name;
		this.start_year = start_year;
		this.end_year = end_year;
		ISO_Code = iSO_Code;
		this.home_wins = home_wins;
		this.away_wins = away_wins;
		this.total_wins = total_wins;
		this.home_losses = home_losses;
		this.away_losses = away_losses;
		this.total_losses = total_losses;
		this.home_ties = home_ties;
		this.away_ties = away_ties;
		this.total_ties = total_ties;
		this.home_matches = home_matches;
		this.away_matches = away_matches;
		this.total_matches = total_matches;
	}

	public String getDisplay_Name() {
		return Display_Name;
	}

	public void setDisplay_Name(String display_Name) {
		Display_Name = display_Name;
	}

	public String getOfficial_Name() {
		return Official_Name;
	}

	public void setOfficial_Name(String official_Name) {
		Official_Name = official_Name;
	}

	public String getStart_year() {
		return start_year;
	}

	public void setStart_year(String start_year) {
		this.start_year = start_year;
	}

	public String getEnd_year() {
		return end_year;
	}

	public void setEnd_year(String end_year) {
		this.end_year = end_year;
	}

	public int getISO_Code() {
		return ISO_Code;
	}

	public void setISO_Code(int iSO_Code) {
		ISO_Code = iSO_Code;
	}

	public int getHome_wins() {
		return home_wins;
	}

	public void setHome_wins(int home_wins) {
		this.home_wins = home_wins;
	}

	public int getAway_wins() {
		return away_wins;
	}

	public void setAway_wins(int away_wins) {
		this.away_wins = away_wins;
	}

	public int getTotal_wins() {
		return total_wins;
	}

	public void setTotal_wins(int total_wins) {
		this.total_wins = total_wins;
	}

	public int getHome_losses() {
		return home_losses;
	}

	public void setHome_losses(int home_losses) {
		this.home_losses = home_losses;
	}

	public int getAway_losses() {
		return away_losses;
	}

	public void setAway_losses(int away_losses) {
		this.away_losses = away_losses;
	}

	public int getTotal_losses() {
		return total_losses;
	}

	public void setTotal_losses(int total_losses) {
		this.total_losses = total_losses;
	}

	public int getHome_ties() {
		return home_ties;
	}

	public void setHome_ties(int home_ties) {
		this.home_ties = home_ties;
	}

	public int getAway_ties() {
		return away_ties;
	}

	public void setAway_ties(int away_ties) {
		this.away_ties = away_ties;
	}

	public int getTotal_ties() {
		return total_ties;
	}

	public void setTotal_ties(int total_ties) {
		this.total_ties = total_ties;
	}

	public int getHome_matches() {
		return home_matches;
	}

	public void setHome_matches(int home_matches) {
		this.home_matches = home_matches;
	}

	public int getAway_matches() {
		return away_matches;
	}

	public void setAway_matches(int away_matches) {
		this.away_matches = away_matches;
	}

	public int getTotal_matches() {
		return total_matches;
	}

	public void setTotal_matches(int total_matches) {
		this.total_matches = total_matches;
	}

	@Override
	public String toString() {
		return "Xora [Display_Name=" + Display_Name + ", Official_Name=" + Official_Name + ", start_year=" + start_year
				+ ", end_year=" + end_year + ", ISO_Code=" + ISO_Code + ", home_wins=" + home_wins + ", away_wins="
				+ away_wins + ", total_wins=" + total_wins + ", home_losses=" + home_losses + ", away_losses="
				+ away_losses + ", total_losses=" + total_losses + ", home_ties=" + home_ties + ", away_ties="
				+ away_ties + ", total_ties=" + total_ties + ", home_matches=" + home_matches + ", away_matches="
				+ away_matches + ", total_matches=" + total_matches + "]";
	}
	
}
