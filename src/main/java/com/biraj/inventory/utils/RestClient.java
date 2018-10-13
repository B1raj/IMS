package com.biraj.inventory.utils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

@Service
public class RestClient {

	private HostnameVerifier allHostsValid = null;
	private SSLContext sslcontext = null;
	private OkHttpClient client = null;

	@Autowired
	Environment env;

	@PostConstruct
	public void init() {

		TrustStrategy trustStratergy = (chain, authType) -> true;
		allHostsValid = (String hostname, SSLSession session) -> true;

		String trustStorePath = env.getRequiredProperty("security.host.truststore.path");
		String trustStorePwd = env.getRequiredProperty("security.host.truststor.pwd");

		try {
			sslcontext = SSLContexts.custom()
					.loadTrustMaterial(new File(trustStorePath),
							trustStorePwd.toCharArray(), trustStratergy)
					.build();
		} catch (final Exception e) {
			System.out.println("Could not create SSLContext");
		}

		client = new OkHttpClient.Builder().sslSocketFactory(sslcontext.getSocketFactory())
				.hostnameVerifier(allHostsValid).connectionPool(new ConnectionPool(0, 1, TimeUnit.NANOSECONDS)).build();
	}
}
