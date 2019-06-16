package com.sample.extend;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

import org.kie.api.runtime.rule.AccumulateFunction;

/**
 * �Զ����Accumulate
 * �����Լ��
 * @author liuzhsh
 *
 */
public class GreatestCommonDivisorAccumulate implements AccumulateFunction {

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public Serializable createContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(Serializable context) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void accumulate(Serializable context, Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reverse(Serializable context, Object value) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getResult(Serializable context) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supportsReverse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Class<?> getResultType() {
		// TODO Auto-generated method stub
		return null;
	}

}
