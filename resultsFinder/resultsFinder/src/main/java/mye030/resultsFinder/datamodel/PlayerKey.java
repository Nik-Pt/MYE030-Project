package mye030.resultsFinder.datamodel;

import jakarta.persistence.Embeddable;

@Embeddable
public class PlayerKey {
    private String scorer;
    private int ISO_Code;
    
	public PlayerKey() {
		super();
		
	}

	public PlayerKey(String scorer, int iSO_Code) {
		super();
		this.scorer = scorer;
		ISO_Code = iSO_Code;
	}

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

	@Override
	public String toString() {
		return "PlayerKey [scorer=" + scorer + ", ISO_Code=" + ISO_Code + "]";
	}
}