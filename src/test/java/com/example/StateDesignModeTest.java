/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:StateDesignModeTest.java  
 * Package Name:com.example 
 * Date:2019年4月4日下午4:36:27  
 * Copyright (c) 2019,  
 *  
*/

package com.example;

/**
 * ClassName:StateDesignModeTest Date: 2019年4月4日 下午4:36:27 
 * 
 * 设计模式：状态模式
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class StateDesignModeTest {
	public static void main(String[] args) {
		Context context = new Context();
		StartState startState = new StartState();
		startState.doAction(context);
		System.out.println(context.getState().toString());
	}
}

interface State {
	void doAction(Context context);
}

class Context {
	private State state;

	public Context() {
		this.state = null;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}

class StartState implements State {

	@Override
	public void doAction(Context context) {
		System.out.println("This is start state!");
		context.setState(this);
	}

	public String toString() {
		return "Start State";
	}
}

class StopState implements State {

	@Override
	public void doAction(Context context) {
		System.out.println("This is stop state!");
		context.setState(this);
	}

	public String toString() {
		return "Stop State";
	}
}
