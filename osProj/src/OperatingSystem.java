import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.Semaphore;


public class OperatingSystem {
	static mutex writing=new mutex();
	static mutex input=new mutex();
	static mutex read=new mutex();
	static mutex print=new mutex();
	static Vector<Process> Readyqueue=new Vector<Process>();
	public static ArrayList<Process> ProcessTable;

//	public static int activeProcess= 0;
	//system calls:
	// 1- Read from File
	@SuppressWarnings("unused")
	public static String readFile(String name) {
		String Data="";
		File file = new File(name);
	 try {
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine())
		{
			Data+= scan.nextLine()+"\n";
		}
		scan.close();
	} catch (FileNotFoundException e) {
		System.out.println(e.getMessage());
	}
		return Data;
	}
	
	// 2- Write into file
	@SuppressWarnings("unused")
	public static void writefile(String name, String data) {
		try
		{
			BufferedWriter BW = new BufferedWriter(new FileWriter(name));
			BW.write(data);
			BW.close();
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}

	}
	//3- print to console
	@SuppressWarnings("unused")
	public static void printText(String text) {

		System.out.println(text);
		
	}
	
	//4- take input
	
	@SuppressWarnings("unused")
	public static String TakeInput() {
		Scanner in= new Scanner(System.in);
		String data = in.nextLine();
		return data;
		
	}
	
	private static void createProcess(int processID){
		Process p = new Process(processID);
		p.setProcessState(p, ProcessState.New);
		ProcessTable.add(p);
		Process.setProcessState(p,ProcessState.Ready);
		Readyqueue.add(p);
		
	}

	public static void schedulingAlgorithm(){
		int i=0;
		while(i<Readyqueue.size()) {
			Process proc=Readyqueue.get(i);
				if(Readyqueue.get(i).suspendflag==true) {
					proc.Resume();
				Readyqueue.get(i).suspendflag=false;	
				}
				else 
				proc.start();
				Readyqueue.remove(i);
			while(proc.isAlive()) {	
			}
			System.out.println("terminated");
		}
	}
	         // null state --- ready
			 // create process -- set process to ready -- add in ready queue +++
		     // flag to see if entered process or not (false)
			 // condition while loop fadya -- run inside loop , to avoid another proceess to enter 
			 // isAlife -- method is running , do nothing 
			 // check flag method -- lopp -- take process from ready queue -- not started yet
			 // check 1st , flag== false , p.start , make flag = true 
			 // set state -- terminate -- add flag==false
			 
			
	
	public static void main(String[] args) {
   		ProcessTable = new ArrayList<Process>();

		createProcess(1);
		createProcess(2);
		createProcess(3);
		createProcess(4);
		createProcess(5);
		schedulingAlgorithm();
		
		}	
}



