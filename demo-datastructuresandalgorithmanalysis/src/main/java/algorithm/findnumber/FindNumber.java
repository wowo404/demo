package algorithm.findnumber;

/**
 * 一列数的规则如下: 1、1、2、3、5、8、13、21、34...... 求第30位数是多少， 用递归算法实现。
 * @author liuzhsh
 */
public class FindNumber {
	
	/**
	 * 比较差的算法
	 */
	private static int find(int pos){
		if(pos < 1){
			return 1;
		}
		return find(pos - 1) + find(pos - 2);
	}

	public static void main(String[] args) {
		
		System.out.println(find(5));

	}

}
