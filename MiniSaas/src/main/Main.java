package main;

import java.sql.SQLException;
import home.Home;

public class Main {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		
		Home home = new Home();
		home.conectarBanco();
		home.listarOpcoes();
		
	}
}
