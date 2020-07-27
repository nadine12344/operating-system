//import java.util.concurrent.Semaphore;


public class Process extends Thread {
	
	public int processID;
    ProcessState status=ProcessState.New;	
    boolean suspendflag = false;
	
	public Process(int m) {
		processID = m;
	}
	public void Suspend()
	  {
	     suspendflag = true;
	     super.suspend();
	  } 

	  public void Resume()
	  {
	    suspendflag = false;
	    super.resume();
	  }
	@Override
	public void run() {
		
		switch(processID)
		{
		case 1:try {
				process1();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}break;
		case 2:try {
				process2();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}break;
		case 3:try{
			process3();}
		       catch (InterruptedException e){
		    	   System.out.println(e.getMessage());
		       }break;
		case 4: try{process4();}
		catch (InterruptedException e){
	    	   System.out.println(e.getMessage());
	       }break;
		case 5:try {
				process5();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}break;
		}

	}
	
	private void process1() throws InterruptedException {
		this.setProcessState(this, ProcessState.Running);
		OperatingSystem.print.wait(this);
		OperatingSystem.printText("Enter File Name: 1");
		OperatingSystem.print.post(this.processID);
		OperatingSystem.input.wait(this);
		String s=OperatingSystem.TakeInput();
		OperatingSystem.input.post(this.processID);
		OperatingSystem.print.wait(this);
		OperatingSystem.read.wait(this);
		OperatingSystem.printText(OperatingSystem.readFile(s));
		OperatingSystem.read.post(this.processID);
		OperatingSystem.print.post(this.processID);
		setProcessState(this,ProcessState.Terminated);
		}
	
	private void process2() throws InterruptedException {
		this.setProcessState(this, ProcessState.Running);
		OperatingSystem.print.wait(this);
		OperatingSystem.printText("Enter File Name: ");
		OperatingSystem.print.post(this.processID);
		OperatingSystem.input.wait(this);
		String filename= OperatingSystem.TakeInput();
		OperatingSystem.input.post(this.processID);
		OperatingSystem.print.wait(this);
		OperatingSystem.printText("Enter Data: ");
		OperatingSystem.print.post(this.processID);
		OperatingSystem.input.wait(this);
		String data= OperatingSystem.TakeInput();
		OperatingSystem.input.post(this.processID);
		OperatingSystem.writing.wait(this);
		OperatingSystem.writefile(filename,data);
		OperatingSystem.writing.post(this.processID);
		setProcessState(this,ProcessState.Terminated);
		}
	private void process3() throws InterruptedException {
		this.setProcessState(this, ProcessState.Running);
		int x=0;
		while (x<301)
		{ 
			OperatingSystem.print.wait(this);
			OperatingSystem.printText(x+"\n");
			OperatingSystem.print.post(this.processID);
			x++;
		}
		setProcessState(this,ProcessState.Terminated);
		}
	
	private void process4() throws InterruptedException {
		this.setProcessState(this, ProcessState.Running);
		int x=500;
		while (x<1001)
		{
			OperatingSystem.print.wait(this);
			OperatingSystem.printText(x+"\n");
			OperatingSystem.print.post(this.processID);
			x++;
		}	
		setProcessState(this,ProcessState.Terminated);
		}
	private void process5() throws InterruptedException {
		this.setProcessState(this, ProcessState.Running);
		OperatingSystem.print.wait(this);
		OperatingSystem.printText("Enter LowerBound: ");
		OperatingSystem.print.post(this.processID);
		OperatingSystem.input.wait(this);
		String lower= OperatingSystem.TakeInput();
		OperatingSystem.input.post(this.processID);	
		OperatingSystem.print.wait(this);
		OperatingSystem.printText("Enter UpperBound: ");
		OperatingSystem.print.post(this.processID);
		OperatingSystem.input.wait(this);
		String upper= OperatingSystem.TakeInput();
		OperatingSystem.input.post(this.processID);
		int lowernbr=Integer.parseInt(lower);
		int uppernbr=Integer.parseInt(upper);
		String data="";
		
		while (lowernbr<=uppernbr)
		{
			data+=lowernbr++ +"\n";
		}
		OperatingSystem.writing.wait(this);
		OperatingSystem.writefile("P5.txt", data);
		OperatingSystem.writing.post(this.processID);
		setProcessState(this,ProcessState.Terminated);
	}
	
	 public static void setProcessState(Process p, ProcessState s) {
	
		 p.status=s;
		 if (s == ProcessState.Terminated)
		 {
			 OperatingSystem.ProcessTable.remove(OperatingSystem.ProcessTable.indexOf(p));
		 }

	}
	 
	 public static ProcessState getProcessState(Process p) {
		 return p.status;
	}
}
