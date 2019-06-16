package org.liu.designpatterns.behavioralschema.chainofresponsibility;

/**
 * 责任链模式（Chain of Responsibility）
 * @author liuzhsh
 */
public class MyHandler extends AbstractHandler implements Handler {
	
	private String name;
	
	public MyHandler(String name){
		this.name = name;
	}

	@Override
	public void operator() {
		System.out.println(name + "done!");
		if(getHandler() != null){
			getHandler().operator();
		}
	}
	
}
