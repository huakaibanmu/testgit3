package model;

public class Client implements IEntity{
	private int id;
	private String name;
	private String address;
	private String tel;
	
	public Client() {
		id = 0;
		name = null;
		address = null;
		tel = null;
		
	}
	
	public Client(int id, String name, String address, String fax, String tel, String postCode, String bankName, String bankAccount) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.tel = tel;
		
	}
	
	
	public int GetId() {
		return id;
	}
	
	public String GetName() {
		return name;
	}
	
	public String GetAddress() {
		return address;
	}
	
	
	public String GetTel() {
		return tel;
	}
	
	public void SetId(int id) {
		this.id = id;
	}
	
	public void SetName(String name) {
		this.name = name;
	}
	
	public void SetAddress(String address) {
		this.address = address;
	}
	
	public void SetTel(String tel) {
		this.tel = tel;
	}
	
	
}
