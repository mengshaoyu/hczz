package com.shield.hczz.base.persistence;

public interface ClueApplyMapper {
	int synchroClue(String applyId);

	int updateResultByPz(String pzid);
}