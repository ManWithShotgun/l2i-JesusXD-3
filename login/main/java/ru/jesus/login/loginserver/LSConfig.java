package ru.jesus.login.loginserver;

import ru.jesus.login.Config;

public class LSConfig extends Config {
	public static void setLSHost(String host) {
		LOGIN_SERVER_HOSTNAME = host;
	}
	public static String getHost() {
		return LOGIN_SERVER_HOSTNAME;
	}

}
