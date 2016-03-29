package test;


public class DemoTest {
	public static void main(String[] args) {
		String str="16204;";
		String[] strs=str.split(";");
		for(String s:strs){
			System.out.println(s);
		}
	}
}
