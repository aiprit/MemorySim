import java.util.*;
public class MainMemory {

	static int size = 16777216;
	String[] data = new String[size];

	public String[] read(int entry,int blocksize){
		
		return Arrays.copyOfRange(data, entry, entry+blocksize);
	}
	public void write(int entry, String value){
		data[entry] = value;
	}
	public String readone(int entry){
		return data[entry];
	}
}
