package com.ljy.excel.domain;

import java.util.Date;

import com.ljy.excel.annotation.ExcelColum;
import com.ljy.excel.annotation.ExcelDataObject;

@ExcelDataObject
public class TestMenu {

	@ExcelColum(name = "menu1")
	private int menu1;

	@ExcelColum(name = "menu2")
	private String menu2;

	@ExcelColum(name = "menu3", isRequired = true)
	private String menu3;

	@ExcelColum(name = "menu4")
	private int menu4;

	@ExcelColum(name = "menu5")
	private String menu5;

	@ExcelColum(name = "menu6")
	private String menu6;

	@ExcelColum(name = "menu7")
	private Date menu7;

	@ExcelColum(name = "menu8")
	private String menu8;

	@ExcelColum(name = "menu9")
	private String menu9;

	public int getMenu1() {
		return menu1;
	}

	public void setMenu1(int menu1) {
		this.menu1 = menu1;
	}

	public String getMenu2() {
		return menu2;
	}

	public void setMenu2(String menu2) {
		this.menu2 = menu2;
	}

	public String getMenu3() {
		return menu3;
	}

	public void setMenu3(String menu3) {
		this.menu3 = menu3;
	}

	public int getMenu4() {
		return menu4;
	}

	public void setMenu4(int menu4) {
		this.menu4 = menu4;
	}

	public String getMenu5() {
		return menu5;
	}

	public void setMenu5(String menu5) {
		this.menu5 = menu5;
	}

	public String getMenu6() {
		return menu6;
	}

	public void setMenu6(String menu6) {
		this.menu6 = menu6;
	}

	public Date getMenu7() {
		return menu7;
	}

	public void setMenu7(Date menu7) {
		this.menu7 = menu7;
	}

	public String getMenu8() {
		return menu8;
	}

	public void setMenu8(String menu8) {
		this.menu8 = menu8;
	}

	public String getMenu9() {
		return menu9;
	}

	public void setMenu9(String menu9) {
		this.menu9 = menu9;
	}

}
