package model;
public class Meal {
	
	private int id;
	private String name;
	private float price;
	private String descs;
	private int cafeId;//ËùÊô²Í¹Ý´úºÅ
	private int num; 
	private float discount;
	private String evaluation;
	public Meal() {
		id = 0;
		name = null;
		price = 0;
		descs = null;
		cafeId = 0;
		num = 0;
		discount = 0;
		evaluation = null;
	}
	
	public Meal(int id, String name, float price, String desc, int cafeId, int num, float discount,String evaluation) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.descs = desc;
		this.cafeId = cafeId;
		this.num = num;
		this.discount = discount;
		this.evaluation = evaluation;
	}
	
	
	public int GetId() {
		return id;
	}
	
	public String GetName() {
		return name;
	}
	
	public float GetPrice() {
		return price;
	}
	
	public String GetDesc() {
		return descs;
	}
	
	public int GetCafeId() {
		return cafeId;
	}
	
	public int  GetNum() {
		return num;
	}
	
	public float GetDiscount() {
		return discount;
	}
	public String GetEvaluation() {
		return evaluation;
	}
	public void SetId(int id) {
		this.id = id;
	}
	
	public void SetName(String name) {
		this.name = name;
	}
	
	public void SetPrice(float price) {
		this.price = price;
	}
	
	public void SetDesc(String desc) {
		this.descs = desc;
	}
	
	public void SetDiscount(float discount) {
		this.discount = discount;
	}
	
	public void SetNum(int num) {
		this.num = num;
	}
	public void SetCafeId(int cafeId) {
		this.cafeId = cafeId;
	}
	public void SetEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
	
	}
	

