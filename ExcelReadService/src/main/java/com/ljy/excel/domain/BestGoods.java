package com.ljy.excel.domain;

import com.ljy.excel.annotation.ExcelColum;
import com.ljy.excel.annotation.ExcelDataObject;

@ExcelDataObject
public class BestGoods {

	@ExcelColum(name = "원산지")
	private String menu1;

	@ExcelColum(name = "상세SPEC")
	private String menu2;

	@ExcelColum(name = "C/T입수")
	private int menu3;

	@ExcelColum(name = "모델이미지")
	private String menu4;

	@ExcelColum(name = "소비자가")
	private int menu5;

	@ExcelColum(name = "공급가")
	private int menu6;

	public String getMenu1() {
		return menu1;
	}

	public void setMenu1(String menu1) {
		this.menu1 = menu1;
	}

	public String getMenu2() {
		return menu2;
	}

	public void setMenu2(String menu2) {
		this.menu2 = menu2;
	}

	public int getMenu3() {
		return menu3;
	}

	public void setMenu3(int menu3) {
		this.menu3 = menu3;
	}

	public String getMenu4() {
		return menu4;
	}

	public void setMenu4(String menu4) {
		this.menu4 = menu4;
	}

	public int getMenu5() {
		return menu5;
	}

	public void setMenu5(int menu5) {
		this.menu5 = menu5;
	}

	public int getMenu6() {
		return menu6;
	}

	public void setMenu6(int menu6) {
		this.menu6 = menu6;
	}

}
