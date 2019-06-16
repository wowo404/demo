package org.liu.designpatterns.behavioralschema.chainofresponsibility;

/**
 * 责任链模式（Chain of Responsibility）
 * @author liuzhsh
 */
public abstract class AbstractHandler {
	
	private Handler handler;

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

}
