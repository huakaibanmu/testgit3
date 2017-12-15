package model;

import java.sql.*;
public class Order{
	private int id;
	private int status;//1�����Ŷ� 2���� 3 ���� 4 ���� 
	private String mealName;
	private int num;
	private int cafeId;//�����͹ݴ��� 
	private int userId;
	private float oldTotalPrice; 
	private float newTotalPrice; 
	private Timestamp time;
	private int ifDel;//�����Ƿ�ȡ��
	
	public Order(int id, int status, String mealName, int num, int cafeId,int userId, float oldTotalPrice, float newTotalPrice,Timestamp time) {
		this.id = id;
		this.status = status;
		this.mealName = mealName;
		this.num = num;
		this.cafeId = cafeId;
		this.userId = userId;
		this.oldTotalPrice = oldTotalPrice;
		this.newTotalPrice = newTotalPrice;
		this.time = time;
		this.ifDel = 0;
	}
	public int GetId() {
		return id;
	}
	
	public int GetStatus() {
		return status;
	}
	
	public String GetmealName() {
		return mealName;
	}
	public int GetmealNum() {
		return num;
	}
	public int GetCafeId() {
		return cafeId;
	}
	public int GetUserId() {
		return userId;
	}
	public float GetOldTotalPrice() {
		return oldTotalPrice;
	}
	public float GetNewTotalPrice() {
		return newTotalPrice;
	}
	public  Timestamp GetTime() {
		return time;
	}
	public int GetifDel() {
		return ifDel;
	}
	
	public void SetId(int id) {
		this.id = id;
	}
	
	public void SetStatus(int status) {
		this.status = status;
	}
	
	public void SetmealName(String mealName) {
		this.mealName = mealName;
	}
	
	public void SetCafeId(int cafeId) {
		this.cafeId = cafeId;
	}
	public void SetUserId(int userId) {
		this.userId = userId;
	}
	public void SetOldTotalPrice(int oldTotalPrice) {
		this. oldTotalPrice = oldTotalPrice;
	}
	public void SetNewTotalPrice(int newTotalPrice) {
		this.newTotalPrice = newTotalPrice;
	}
	public void SetTime(Timestamp time) {
		this.time = time;
	}
	public void SetifDel(int ifDel) {
		this.ifDel = ifDel;
	}
}
