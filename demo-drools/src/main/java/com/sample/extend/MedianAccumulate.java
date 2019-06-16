package com.sample.extend;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

import org.kie.api.runtime.rule.AccumulateFunction;

/**
 * ����λ����Accumulate
 * @author liuzhsh
 *
 */
public class MedianAccumulate implements AccumulateFunction {

	public static class MedianData implements Externalizable {
		public int count;//������
		public Number[] numberList;//�����Ľ��,TODO �����
		@Override
		public void writeExternal(ObjectOutput out) throws IOException {
			out.write(count);
			out.writeObject(numberList);
		}

		@Override
		public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
			count = in.readInt();
			numberList = (Number[]) in.readObject();
		}
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {

	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

	}

	@Override
	public Serializable createContext() {
		return new MedianData();
	}

	@Override
	public void init(Serializable context) throws Exception {
		MedianData data = (MedianData) context;
		data.count = 0;
		data.numberList = new Number[0];
	}

	@Override
	public void accumulate(Serializable context, Object value) {
		//��μ���д������
		//����λ����1.����2.���count����������ȡ�ڣ�n+1��/2��Ϊ��λ������countΪż��������λ���ǵ�(n/2)�����͵�[(n/2)+1]������ƽ��ֵ
		MedianData data = (MedianData) context;//TODO �����
	}

	@Override
	public void reverse(Serializable context, Object value) throws Exception {
		// reverse�Ĵ���

	}

	@Override
	public Object getResult(Serializable context) throws Exception {
		// ��ȡ������������
		MedianData data = (MedianData) context;
		if(data.count % 2 == 0){
			return data.numberList[(data.count + 1) / 2];
		} else {
			Number first = data.numberList[data.count / 2];
			Number second = data.numberList[(data.count / 2) + 1];
			return (first.doubleValue() + second.doubleValue()) / 2 ;
		}
	}

	@Override
	public boolean supportsReverse() {
		// �Ƿ�֧��reverse
		return false;
	}

	@Override
	public Class<?> getResultType() {
		// ���ؽ���Ǹ�ʲô����
		return Number.class;
	}

}
