package com.mm.mealapp.api;

/**
 * ���ﳵʵ����
 */
public class ShopCar {
	private Integer _id;
	/** ��Ʒ���� **/
	private String title;
	/** ���ʱ�� **/
	private String time;
	/** ��Ʒ���� **/
	private Integer number;
	/** ��Ʒ�۸� **/
	private Integer price;

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

}
