package com.and9.tckms.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * 创建Jetty Server的工厂类.
 * 
 * @author fly
 */
public class JettyFactory {

	private JettyFactory() {
	}
	
	/**
	 * 创建用于开发运行调试的Jetty Server, 以src/main/webapp为Web应用目录.
	 */
	public static Server buildNormalServer(int port, String contextPath) {
		Server server = new Server(port);
		WebAppContext webContext = new WebAppContext("src/main/webapp", contextPath);
		webContext.setClassLoader(Thread.currentThread().getContextClassLoader());
		server.setHandler(webContext);
		server.setStopAtShutdown(true);
		System.out.println();
		return server;
	}

	/**
	 * 创建用于Functional Test的Jetty Server:
	 * 1.以src/main/webapp为Web应用目录.
	 * 2.以test/resources/web.xml指向applicationContext-test.xml创建测试环境.
	 */
	public static Server buildTestServer(int port, String contextPath) {
		Server server = buildNormalServer(port, contextPath);
		((WebAppContext) server.getHandler()).setDescriptor("src/test/resources/web.xml");
		return server;
	}
}
