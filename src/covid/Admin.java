package covid;

public class Admin {
	private static int id_admin = 0;
	private String login;
	private String password;

	public Admin() {
		super();
		this.id_admin = id_admin + 1;
	}

	public Admin(String login, String pass) {
		super();
		this.id_admin = id_admin + 1;
		setLogin(login);
		setPassword(pass);
	}

	public static int getId_admin() {
		return id_admin;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public static void setId_admin(int id_admin) {
		Admin.id_admin = id_admin;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	public boolean passwordIsValid(String pass) {
		if (pass.contains("orsys"))
		return true;
		else return false;
	}
	
	public void setPassword(String password) {
		if (passwordIsValid(password)) this.password = password;
		else System.out.println("le mot de passe n'est pas valide");
	}

	public static boolean isAdmin4real(Admin admin) {
		return (admin.getLogin().equals("admin") && admin.getPassword().contains("orsys"));
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Admin [login=" + login + ", password=" + password + "]";
	}

	public boolean isAdmin() {
		if ((login ==null)&&(password==null))
		return false;
		else return true;
	}

}
