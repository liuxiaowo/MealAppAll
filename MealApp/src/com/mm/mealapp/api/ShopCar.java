package com.mm.mealapp.api;

/**
 * 购物车实体类
 */
public class ShopCar {
	private Integer _id;
	/** 商品标题 **/
	private String title;
	/** 添加时间 **/
	private String time;
	/** 商品数量 **/
	private Integer number;
	/** 商品价格 **/
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
