#Memory Simulation
Name: Parit Burintrathikul
CS 250 Memory Simulation

Java Implementation of Cache Simulation

The file input has an no automatic .txt added so if the file has a .txt or something add appropriate endings
The simulation is done through the Cacheism.java file


Test case 1:
args: test2.txt 1024 4 32
store 0x1a25bb 4 c77eaabb
load 0x1a25bb 4
load 0x1a25bb 4

Output:
store 0x1a25bb miss
load 0x1a25bb miss c77eaabb
load 0x1a25bb hit c77eaabb

TEST2:
args: test2.txt 1 512 2
store 0x1a25bb 1 c77e
store 0x0 1 123456
load 0x0 1
load 0x0 1
load 0x1a25bb 1

Output:
store 0x1a25bb miss
store 0x0 miss
load 0x0 miss 12
load 0x0 hit 12
load 0x1a25bb miss c7


Test case 3:
args: test2.txt 1024 4 32
store 0x1a25bb 1 c77e
load 0x1a25bb 1
store 0x1a25bb 1 45
load 0x1a25bb 1

Output:
store 0x1a25bb miss
load 0x1a25bb miss c7
store 0x1a25bb hit
load 0x1a25bb hit 45



Test case 4:

rgs: tracefile.txt 1024 4 32
store 0x1a25bb 4 c77eaabb
store 0xFA25A0 4 abcdef90
store 0xC225A0 4 12345678
load 0x1a25bb 4
load 0xFA25A0 4
load 0xFA25A0 4
load 0xC225A0 4
load 0x1a25bb 4
load 0xC225A0 4
load 0xFA25A0 4

Output:
store 0x1a25bb miss
store 0xFA25A0 miss
store 0xC225A0 miss
load 0x1a25bb miss c77eaabb
load 0xFA25A0 miss abcdef90
load 0xFA25A0 hit abcdef90
load 0xC225A0 miss 12345678
load 0x1a25bb miss c77eaabb
load 0xC225A0 hit 12345678
load 0xFA25A0 miss abcdef90


