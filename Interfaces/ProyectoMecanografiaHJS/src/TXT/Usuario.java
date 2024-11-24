package TXT;

public class Usuario {
	private String id;
	private String name;
	private String pass;
	
	
	public Usuario(String string, String name, String pass) {
		//super();
		this.id = string;
		this.name = name;
		this.pass = pass;
	}


	public Usuario(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;

	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", name=" + name + ", pass=" + pass + "]";
	}
	
	
	
	
}
