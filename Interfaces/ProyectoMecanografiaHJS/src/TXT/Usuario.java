package TXT;

public class Usuario {
	private String id;
	private String name;
	private String pass;
	private String mail;
	
	
	public Usuario(String string, String name, String pass, String mail) {
		//super();
		this.id = string;
		this.name = name;
		this.pass = pass;
		this.mail = mail;
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


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", name=" + name + ", pass=" + pass + ", mail=" + mail + "]";
	}



	
	
	
}
