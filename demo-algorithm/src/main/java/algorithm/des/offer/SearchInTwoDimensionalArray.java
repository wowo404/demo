package algorithm.des.offer;

public class SearchInTwoDimensionalArray {

	public static void main(String[] args) {

		/**
		 * 1,2,3,4,5
		 * 2,3,4,5,6
		 * 3,4,5,6,7
		 * 4,5,6,7,8
		 * 5,6,7,8,9
		 */
		Integer[][] twoDimensional = new Integer[5][5];
		for (int i = 0; i < twoDimensional.length; i++) {
			Integer[] oneDemensional = twoDimensional[i];
			for (int j = 0; j < oneDemensional.length; j++) {
				twoDimensional[i][j] = j + 1;
			}
		}
		
		Integer random = 3;
		//获取二维数组的横轴长度和纵轴长度
		Integer vertical = twoDimensional.length;//纵轴
		Integer horizontal = twoDimensional[0].length;//横轴
		
		Integer min = twoDimensional[0][0];
		Integer max = twoDimensional[vertical-1][horizontal-1];
		
		if(random < min || random > max){
			System.out.println("数字" + random + "不在二维数组twoDimensional中");
		} else {
			System.out.println("数字" + random + "在二维数组twoDimensional中");
		}
		
		
	}

}
