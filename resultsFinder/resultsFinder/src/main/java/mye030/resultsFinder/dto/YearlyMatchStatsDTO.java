package mye030.resultsFinder.dto;

public class YearlyMatchStatsDTO {
	
	private long total_year_matches;
	private long total_year_draws;
	private long had_shootouts;
	private long total_year_wins;
	private long total_year_losses;
	
	public YearlyMatchStatsDTO(long total_year_matches, long total_year_draws, long had_shootouts) {
		super();
		this.total_year_matches = total_year_matches;
		this.total_year_draws = total_year_draws;
		this.had_shootouts = had_shootouts;
	}

	public YearlyMatchStatsDTO(long total_year_matches, long total_year_draws, long had_shootouts,
			long total_year_wins, long total_year_losses) {
		super();
		this.total_year_matches = total_year_matches;
		this.total_year_draws = total_year_draws;
		this.had_shootouts = had_shootouts;
		this.total_year_wins = total_year_wins;
		this.total_year_losses = total_year_losses;
	}


	public long getTotal_year_wins() {
		return total_year_wins;
	}

	public void setTotal_year_wins(long total_year_wins) {
		this.total_year_wins = total_year_wins;
	}

	public long getTotal_year_losses() {
		return total_year_losses;
	}

	public void setTotal_year_losses(long total_year_losses) {
		this.total_year_losses = total_year_losses;
	}

	public long getTotal_year_matches() {
		return total_year_matches;
	}

	public void setTotal_year_matches(long total_year_matches) {
		this.total_year_matches = total_year_matches;
	}

	public long getTotal_year_draws() {
		return total_year_draws;
	}

	public void setTotal_year_draws(long total_year_draws) {
		this.total_year_draws = total_year_draws;
	}

	public long getHad_shootouts() {
		return had_shootouts;
	}

	public void setHad_shootouts(long had_shootouts) {
		this.had_shootouts = had_shootouts;
	}
}
