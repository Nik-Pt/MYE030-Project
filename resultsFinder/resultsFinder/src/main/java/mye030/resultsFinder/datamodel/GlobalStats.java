package mye030.resultsFinder.datamodel;

import org.springframework.data.annotation.Immutable;
import jakarta.persistence.*;

@Entity
@Immutable
@Table(name = "GlobalStats")
public class GlobalStats {
	
	@Id
	@Column(name = "ISO_Code")
	private int ISO_Code;
	
	@Column(name = "Display_Name")
	private String Display_Name;

	@Column(name = "total_wins")
	private int total_wins;
	
	@Column(name = "score")
	private int score;
	
	@Column(name = "years_playing")
	private int years_playing;

	public GlobalStats() {
		super();
	}

	public GlobalStats(int iSO_Code, String display_name, int total_Wins, int Score, int years_playing) {
		super();
		this.ISO_Code = iSO_Code;
		this.Display_Name = display_name;
		this.total_wins = total_Wins;
		this.score = Score;
		this.years_playing = years_playing;
	}

	public int getISO_Code() {
		return ISO_Code;
	}

	public void setISO_Code(int iSO_Code) {
		ISO_Code = iSO_Code;
	}
	
	public String getDisplay_Name() {
		return Display_Name;
	}

	public void setDisplay_Name(String display_Name) {
		Display_Name = display_Name;
	}

	public int getTotal_wins() {
		return total_wins;
	}

	public void setTotal_wins(int total_wins) {
		this.total_wins = total_wins;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int Score) {
		score = Score;
	}

	public int getYears_playing() {
		return years_playing;
	}

	public void setYears_playing(int years_playing) {
		this.years_playing = years_playing;
	}

	@Override
	public String toString() {
		return "GlobalStats [ISO_Code=" + ISO_Code + ", Total_Wins=" + total_wins + ", Score=" + score
				+ ", years_playing=" + years_playing + "]";
	}
	
}
