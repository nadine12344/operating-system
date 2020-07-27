import java.util.Queue;
import java.util.Vector;

public class mutex {
	

boolean locked = false;
Vector<Process> queue=new Vector<Process>();
int id=0;

	mutex() {
		locked = (false);
	}

	public void wait(Process p) throws InterruptedException {
		if(locked==true) {
			p.status=ProcessState.Waiting;
			queue.add(p);
			System.out.println();
			p.Suspend();
		}
		else {
			this.id=p.processID;
		locked=true;
		}
	}

	public void post(int id) {
		if(this.id==id) {
			if(this.queue.size()>0) {
	
			OperatingSystem.Readyqueue.add(queue.get(0));
		queue.get(0).status=ProcessState.Ready;
		//queue.get(0).Resume();
		queue.remove(0);
		}
		locked = false;
	}}
}
