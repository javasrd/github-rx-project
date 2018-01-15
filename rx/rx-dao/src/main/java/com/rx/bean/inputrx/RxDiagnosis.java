package com.rx.bean.inputrx;

import java.util.List;

public class RxDiagnosis {
	private List<RxDisease> diagnosis;  //疾病列表

	public List<RxDisease> getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(List<RxDisease> diagnosis) {
		this.diagnosis = diagnosis;
	}
	
}
