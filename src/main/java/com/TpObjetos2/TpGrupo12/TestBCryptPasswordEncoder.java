package com.TpObjetos2.TpGrupo12;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestBCryptPasswordEncoder {

	public static void main(String[] args) {
		BCryptPasswordEncoder password = new BCryptPasswordEncoder();
		System.out.println(password.encode("lourdes"));
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		System.out.println(pe.encode("user"));

	}
}