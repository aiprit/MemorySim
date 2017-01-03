import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Cacheism {
	HashMap<Integer, Block[]> cachemap = new HashMap<Integer,Block[]>();
	int numblocks; 
	int numsets; 
	int setindex;  
	int blockoff; 
	int Counter;
	int blocksize;
	BufferedReader br ;
	MainMemory memory;
	public Cacheism(String f, int cachesize,int setas, int blocksize) {
		this.numblocks = cachesize*1024/blocksize;
		this.numsets = numblocks/setas;
		if(numsets == 0)
			numsets =1;
		this.setindex = (int) (Math.log(numsets)/Math.log(2));
		this.blockoff = (int) (Math.log(blocksize)/Math.log(2));
		try {
			this.br = new BufferedReader (new FileReader(f));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.Counter = 0;
		this.blocksize = blocksize;
		this.memory = new MainMemory();
	}
public void cacheinput(String f, int cachesize,int setas, int blocksize) {
	
	String line;
	
	for(int x = 0; x<numsets;x++){
		cachemap.put(x, new Block[setas]);
	}
	try {
		while(( line = br.readLine()) != null){
			String [] arg = line.split(" ");
			if(arg.length == 4)
				cacherun(arg[0],arg[1].substring(2,arg[1].length()),Integer.parseInt(arg[2]),arg[3],setindex,blockoff);
			else
				cacherun(arg[0],arg[1].substring(2,arg[1].length()),Integer.parseInt(arg[2]),null,setindex,blockoff);
		}
	} catch (NumberFormatException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		br.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void cacherun(String command,String hexaddress, int size, String value, int setoffset,int blockoff)	{
	System.out.print(command+ " 0x"+ hexaddress + " ");
	String address = hextobi.hexToBinary(hexaddress);
	int setindex = 0;
	if(setoffset != 0)
		hextobi.binarytodeci(address.substring(address.length()-setoffset-blockoff,address.length()-blockoff));
	int tag = hextobi.binarytodeci(address.substring(0,address.length()-setoffset-blockoff));
	int offset =0;
	if(offset >=0)
		offset =  hextobi.binarytodeci(address.substring(address.length()-blockoff,address.length()));
	if(command.equals("load")){
		Counter++;
		Block[] blocks = cachemap.get(setindex);
		for(int x=0;x<blocks.length;x++){
			if(blocks[x]!=null){
				if (blocks[x].getTag() == tag){
					System.out.print("hit ");
					for(int y = 0;y<size;y++){
						System.out.print(blocks[x].read(offset+y,Counter));
					}
					System.out.println(" ");
					return;
				}
			}
			}
		System.out.print("miss ");
		for(int z = 0;z<blocks.length;z++){
			if(blocks[z]==null){
				
					Block block = new Block(blocksize);
					block.writeBlock(tag, memory.read(hextobi.binarytodeci(address),blocksize),Counter);
					blocks[z] = block;
					cachemap.put(setindex, blocks);
					for(int y = 0;y<size;y++){
						System.out.print(block.read(offset+y,Counter));
					}
					System.out.println(" ");
					return;
				
			}
		}
		int LRU = 100000;
		int set = 0;
		for(int z = 0;z<blocks.length;z++){
			if(blocks[z]!=null){
				if(blocks[z].getRecentUse()<LRU){
					LRU = blocks[z].getRecentUse();
					set = z;
				}
			}
		}
					Block block = new Block(blocksize);
					block.writeBlock(tag, memory.read(hextobi.binarytodeci(address),blocksize),Counter);
					blocks[set] = block;
					cachemap.put(setindex, blocks);
					for(int y = 0;y<size;y++){
						System.out.print(block.read(offset+y,Counter));
					}
					System.out.println(" ");
				return;
				
			
		
	}
		
	else if(command.equals("store")){
		Counter++;
		boolean hit = false;
		Block[] blocks = cachemap.get(setindex);
		for(int x=0;x<blocks.length;x++){
			if(blocks[x]!=null){
				if (blocks[x].getTag() == tag){
					System.out.print("hit ");
					hit = true;
					for(int w = 0;w<size;w++){
						blocks[x].write(offset+w,value.substring(w*2,w*2+2),Counter);
					}
					System.out.println(" ");
				}		
			}
			
		}
		if(hit == false)
			System.out.println("miss ");
		for(int w =0;w<size;w++){
			memory.write(hextobi.binarytodeci(address)+w+offset, value.substring(w*2, w*2+2));
		}
		
	}
}
	
public static void main(String[] args) {
	//String f = args[1] ;
    String f = "C:/users/Parit/workspace/Cacheism/src/" +args[1] ;
	int cachesize = Integer.parseInt(args[2]); 
	int setass = Integer.parseInt(args[3]);
	int block = Integer.parseInt(args[4]);
	Cacheism yo = new Cacheism(f,cachesize,setass,block);
	yo.cacheinput(f,cachesize,setass,block);
}

}