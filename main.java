//Kareem Zaiter
//MIPS Disassembler

public class main
{

	public main(){

		int hxIn[]={0x022DA822, 0x8EF30018, 0x12A70004, 0x02689820, 0xAD930018, 0x02697824, 0xAD8FFFF4, 
				0x018C6020, 0x02A4A825, 0x158FFFF6, 0x8E59FFF0};
				
		int l = hxIn.length;

		for(short i=0; i<l;i++){
			int instruct = hxIn[i];
			int opcode = instruct & 0xFC000000;
			int sOpcode = opcode >>> 26;

			int addr=0x7A060+i*4;
			String addrSt = Integer.toHexString(addr).toUpperCase();

			switch(sOpcode)
			{
			case 0x00:
				//R-Format
			{   
				int s=(instruct & 0x3E00000)>>>21;
				int t=(instruct & 0x1F0000)>>>16;
				int d=(instruct & 0xF800)>>>11;
				int funct=(instruct & 0x3F);
				switch(funct) 
				{
				case 0x20: //add

					System.out.printf("%s: add $%d, $%d, $%d\n",addrSt,d,s,t);
					break;

				case 0x22: //sub

					System.out.printf("%s: sub $%d, $%d, $%d\n",addrSt,d,s,t);
					break;

				case 0x24: //and

					System.out.printf("%s: and $%d, $%d, $%d\n",addrSt,d,s,t);
					break;

				case 0x25: //or

					System.out.printf("%s: or  $%d, $%d, $%d\n",addrSt,d,s,t);
					break;

				case 0x2A: //slt

					System.out.printf("%s: slt $%d, $%d, $%d\n",addrSt,d,s,t);
					break;

				}

				break;
			}     
			case 0x23:  //lw
			case 0x2B:  //sw
			case 0x04:  //beq
			case 0x05:  //bne
			{
				int s=(instruct & 0x3E00000)>>>21;
				int t=(instruct & 0x1F0000)>>>16;
				short constant=(short) (instruct & 0xFFFF);
				short regOff=(short) ((instruct & 0xFFFF)<<2);

				//System.out.println(constant);
				//System.out.println(negConstant);
				//System.out.println(regOff);
				switch(sOpcode) 
				{
				case 0x23:

					System.out.printf("%s: lw $%d, %d($%d)\n",addrSt,t,constant,s);
					break;

				case 0x2B:
					//System.out.println(constant);
					System.out.printf("%s: sw $%d, %d($%d)\n",addrSt,t,constant,s);
					break;

				case 0x04:
					//System.out.println(constant);
					System.out.printf("%s: beq $%d, $%d, address %s\n",addrSt,s,t,Integer.toHexString(regOff+(addr+4)));
					break;

				case 0x05:
					//System.out.println(constant);
					System.out.printf("%s: bne $%d, $%d, address %s\n",addrSt,s,t,Integer.toHexString(regOff+(addr+4)));
					break;

				}
				break;
			}
		  }
		}
	}
	public static void main(String[] args) {
		
		main lo = new main();
	}
}
