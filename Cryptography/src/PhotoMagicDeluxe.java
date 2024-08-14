import org.apache.commons.lang3.StringUtils;


public class PhotoMagicDeluxe {
	
	private final static  String base64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	
	public static Picture transform(Picture pic, String password, int tap) {
		String binarySequence = "";
		
		for(int e = 0; e < password.length(); e++) {
			String temp = Integer.toBinaryString(base64.indexOf(password.charAt(e)));
			while(temp.length()<6) {
				temp = "0" + temp;
			}
			binarySequence += temp;
		}
		
		return PhotoMagic.transform(pic, new LFSR(binarySequence, tap));
	}
	
	public static void main(String[] args) {
		PhotoMagicDeluxe.transform(new Picture("mystery.png"), "OPENSESAME", 58).show();
	}
	
}	
