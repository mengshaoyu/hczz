package com.shield.hczz.ops.qvo;

import com.shield.frame.common.qvo.DataGridQO;

public class OpsQO extends DataGridQO {
	private String sorter;
	private String order;

	public String getSorter() {
		return sorter;
	}

	public void setSorter(String sorter) {
		this.sorter = sorter;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
