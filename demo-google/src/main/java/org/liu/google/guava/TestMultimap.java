package org.liu.google.guava;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class TestMultimap {
	
	public void multimap(){
		Multimap<Integer, Info> multimap = ArrayListMultimap.create();
		
		Info info1 = new Info();
		info1.setId(1);
		multimap.put(1, info1);
		
		Info info2 = new Info();
		info2.setId(2);
		multimap.put(1, info2);
		
		Info info3 = new Info();
		info3.setId(3);
		multimap.put(2, info3);
		
		Set<Integer> keys = multimap.keySet();
		for (Integer key : keys) {
			List<Info> list = (List<Info>) multimap.asMap().get(key);
			System.out.println("key:" + key + ",list:" + list);
		}
		System.out.println("multimap keys:" + multimap.keys());
		Collection<Info> list = multimap.get(1);
		list.forEach(info -> System.out.println(info.getId()));
	}
	
	public static void main(String[] args) {
		TestMultimap test = new TestMultimap();
		test.multimap();
	}
	
	private class Info{
		private Integer id;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
	}

}
