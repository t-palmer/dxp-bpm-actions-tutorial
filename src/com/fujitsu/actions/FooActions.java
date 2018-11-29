package com.fujitsu.actions;

import com.fujitsu.iflow.server.intf.ServerEnactmentContext;

public class FooActions {

	public static String addFoo(String inputString) {
		return inputString + "Foo";
	}

	public static String appendToProcessName(ServerEnactmentContext sec, String suffix) throws Exception {
		return sec.getProcessName() + suffix;
	}

	public static void main(String[] args) throws Exception {
		String testingFoo = FooActions.addFoo("Testing");
		System.out.println(testingFoo);

		TestSeverEnactmentContext testSec = new TestSeverEnactmentContext();
		String processName = FooActions.appendToProcessName(testSec, " Testing");
		System.out.println("Process Name with suffix: "+ processName);

	}

}
