package algorithm.des.offer;

public class ReplaceBlankSpaceInString {
	
	public String replace(String source){
		return source.replace(" ", "%20");
	}

	public static void main(String[] args) {

		ReplaceBlankSpaceInString u = new ReplaceBlankSpaceInString();
		System.out.println(u.replace("We Are The World!"));
		
	}

}
