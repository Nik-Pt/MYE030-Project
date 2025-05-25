package mye030.resultsFinder.dto;

public class WinYearRatioStatsDTO {

	private String Display_Name;
	private int total_wins;
	private int years_playing;
	private double winsYearsRatio;

	public WinYearRatioStatsDTO(String display_Name, int total_wins, int years_playing, double winsYearsRatio) {
		super();
		Display_Name = display_Name;
		this.total_wins = total_wins;
		this.years_playing = years_playing;
		this.winsYearsRatio = winsYearsRatio;
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
	
	public int getYears_playing() {
		return years_playing;
	}
	
	public void setYears_playing(int years_playing) {
		this.years_playing = years_playing;
	}
	
	public double getWinsYearsRatio() {
		return winsYearsRatio;
	}
	
	public void setWinsYearsRatio(double winsYearsRatio) {
		this.winsYearsRatio = winsYearsRatio;
	}
	
}
