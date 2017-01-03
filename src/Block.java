public class Block {
private int tag;
private boolean isValid;
private int recentUse; // higher values are more recently used
private String[] bytedata;

public Block(int blocksize){
isValid = false; 
recentUse = 0; // 
bytedata = new String[blocksize];


}

public String read(int offset, int useCounter) {
recentUse = useCounter;
if(bytedata[offset] == null){
	return "00";
}
else
	return bytedata[offset];
}

public void write(int offset, String data, int useCounter) {
recentUse = useCounter;
bytedata[offset] = data;
isValid = true;

}
public void writeBlock(int tag, String[] data,int usecounter) {
	this.tag = tag;
	this.bytedata = data;
	isValid = true;
	recentUse = usecounter;

}
public String[] readAllData() {
return bytedata;
}
protected int getTag() {
return tag;
}

protected int getRecentUse() {
return recentUse;
}

protected boolean isValid() {
return isValid;
}
}
