import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AlramDial extends JDialog implements ActionListener {
	JPanel Pane = new JPanel();

	JLabel alramLb, dateLb, timeLb, commentLb;
	JLabel dateDataLb, timeDataLb, commentDataLb;

	JButton OKBtn;

	public AlramDial(int row) {
		OKBtn = new JButton("확인");
		OKBtn.addActionListener(this);

		alramLb = new JLabel("요청 알람");

		dateLb = new JLabel("날짜: ");
		timeLb = new JLabel("시간: ");
		commentLb = new JLabel("내용: ");

		dateDataLb = new JLabel(MainFrame.data[row - 1][0].toString());
		timeDataLb = new JLabel(MainFrame.data[row - 1][0].toString());
		commentDataLb = new JLabel(MainFrame.data[row - 1][2].toString());

		alramLb.setFont(new Font("나눔고딕", Font.BOLD, 14));

		OKBtn.addActionListener(this);

		alramLb.setBounds(60, 5, 100, 25);
		dateLb.setBounds(30, 45, 100, 25);
		dateDataLb.setBounds(70, 45, 100, 25);
		timeLb.setBounds(30, 80, 100, 25);
		timeDataLb.setBounds(70, 8, 100, 25);
		commentLb.setBounds(30, 115, 100, 25);
		commentDataLb.setBounds(70, 975, 100, 60);

		Pane.add(alramLb);
		Pane.add(dateLb);
		Pane.add(timeLb);
		Pane.add(commentLb);
		Pane.add(dateDataLb);
		Pane.add(timeDataLb);
		Pane.add(commentDataLb);
		Pane.add(OKBtn);

		add(Pane);
		Pane.setLayout(null);

		this.setSize(200, 280);
		this.setModal(true);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==OKBtn) {
			setModal(false);
			this.dispose();
		}
	}

}
