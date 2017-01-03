
public class hextobi {
	public static String hexToBinary(String hex){
			if(hex == "0")
				return "000000000000000000000000";
		    int i = Integer.parseInt(hex, 16);
		    String bin = Integer.toBinaryString(i);
		    while(bin.length()<24){
		    	bin = "0"+bin;
		    }
		    return bin;
		}
	public static int binarytodeci(String binary){
		int ans= Integer.parseInt(binary, 2);
		return ans;
		}
}
