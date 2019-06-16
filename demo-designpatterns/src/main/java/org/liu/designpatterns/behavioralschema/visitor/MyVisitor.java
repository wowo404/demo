package org.liu.designpatterns.behavioralschema.visitor;

/**
 * 访问者模式（Visitor）
 * @author liuzhsh
 */
public class MyVisitor implements Visitor {

	@Override
	public void visit(Subject subject) {
		System.out.println("visit the subject："+subject.getSubject());
	}

}
