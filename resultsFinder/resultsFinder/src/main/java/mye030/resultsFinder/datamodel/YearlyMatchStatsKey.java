package mye030.resultsFinder.datamodel;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class YearlyMatchStatsKey implements Serializable {
	private int ISO_Code;
	private int year;
	
	public YearlyMatchStatsKey() {
		super();
	}

	public YearlyMatchStatsKey(int iSO_Code, int year) {
		super();
		ISO_Code = iSO_Code;
		this.year = year;
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

	@Override
	public String toString() {
		return "YearlyMatchStatsKey [ISO_Code=" + ISO_Code + ", year=" + year + "]";
	}

}
