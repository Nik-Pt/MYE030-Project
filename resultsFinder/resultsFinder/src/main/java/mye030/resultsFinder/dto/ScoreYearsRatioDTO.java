package mye030.resultsFinder.dto;

public class ScoreYearsRatioDTO {
	
	private String Display_Name;
	private int score;
	private int years_playing;
	private double scoreYearsRatio;
	
	public ScoreYearsRatioDTO(String display_Name, int score, int years_playing, double scoreYearsRatio) {
		super();
		Display_Name = display_Name;
		this.score = score;
		this.years_playing = years_playing;
		this.scoreYearsRatio = scoreYearsRatio;
	}

	public String getDisplay_Name() {
		return Display_Name;
	}

	public void setDisplay_Name(String display_Name) {
		Display_Name = display_Name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getYears_playing() {
		return years_playing;
	}

	public void setYears_playing(int years_playing) {
		this.years_playing = years_playing;
	}

	public double getScoreYearsRatio() {
		return scoreYearsRatio;
	}

	public void setScoreYearsRatio(double scoreYearsRatio) {
		this.scoreYearsRatio = scoreYearsRatio;
	}
}
