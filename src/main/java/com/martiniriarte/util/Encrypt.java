package com.martiniriarte.util;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class Encrypt {

	private static Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

	public static String encryptPassword(String password) {
		return argon.hash(8, 1024, 2, password);
	}

	public static boolean checkPassword(String passEncrypted, String password) {
		return argon.verify(passEncrypted, password);
	}
}
