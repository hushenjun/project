package com.java.model;

public class User {
		private int id;
		private String useName;
		private String password;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getUseName() {
			return useName;
		}
		public void setUseName(String useName) {
			this.useName = useName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public User() {
			super();
			
		}
		public User(String useName, String password) {
			super();
			this.useName = useName;
			this.password = password;
		}
		
		public User(int id, String useName, String password) {
			super();
			this.id = id;
			this.useName = useName;
			this.password = password;
		}
		public User(int login_id) {
			super();
			this.id = login_id;
		}

		
		
		
}
