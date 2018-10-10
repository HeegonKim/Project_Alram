import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Alram extends Thread implements Runnable {
	private volatile Thread timer;
	private SimpleDateFormat formatter;
	private String lastdate;
	private Date currentDate;
	private Date startTime;
	private String nowTime;
	private boolean OnOff = false;

	public void start() {
		timer = new Thread(this);
		timer.start();
	}

	public void run() {
		System.out.println("알람 시작");
		AlramDial alramDial;
		Thread me = Thread.currentThread();
		while(timer == me && OnOff == false) {
			Thread.currentThread();
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			formatter= new SimpleDateFormat("HH:mm:ss",Locale.getDefault());
			Date newDate = new Date();
			nowTime = formatter.format(newDate);
			
			try {
				startTime = formatter.parse(addDial.time.getText()+":00");
				long ST=startTime.getTime();
				long NT=formatter.parse(nowTime).getTime();
				long diff = ST - NT;
				long sec = diff/1000;
				System.out.println("남은 시간: "+sec);
				
				if(ST==NT) {
					System.out.println("알람 울림");
					OnOff = true;
					alramDial = new AlramDial(MainFrame.row);
					Thread.interrupted();
				}
			}catch(ParseException e) {
				e.printStackTrace();
			}
		}
	}

}
