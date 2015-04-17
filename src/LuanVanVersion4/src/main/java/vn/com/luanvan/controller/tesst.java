package vn.com.luanvan.controller;


public class tesst {

	public static void main(String[] args)  {
		
		String str ="[";
			for(int i = 0; i < 5; i++){
				str+="{\"value\":"+i+"}";
			}
		str+="]";
		System.out.print(str);
	}

}
