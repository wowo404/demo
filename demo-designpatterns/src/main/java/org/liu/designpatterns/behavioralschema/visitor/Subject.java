package org.liu.designpatterns.behavioralschema.visitor;

/**
 * 访问者模式（Visitor）
 * @author liuzhsh
 */
public interface Subject {
	
	public void accept(Visitor visitor);
	
	public String getSubject();

}
