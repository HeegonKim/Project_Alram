import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.datatype.DatatypeConfigurationException;

public class MainFrame extends JFrame implements ActionListener {
	// 메뉴바
	JMenu menu;
	JMenuBar menuBar;
	JMenuItem save, ExitItem;

	// 버튼
	JButton addBtn, modBtn, delBtn;

	// 테이블
	static int row = 0, srow;
	static String title[] = { "날짜", "시간", "내용", "On/Off" };
	static Object data[][];
	static ArrayList<Object[]> list = new ArrayList<Object[]>(); // ArrayList 속 배열

	static DefaultTableModel dtm;
	static JTable table;
	JCheckBox OnOffBox;

	JScrollPane sp;

	addDial dial;

	public MainFrame() throws IOException {
		super("Alram Project in KB국민카드");

		CreateMenu();

		addBtn = new JButton("추가");
		modBtn = new JButton("수정");
		delBtn = new JButton("삭제");

		addBtn.addActionListener(this);
		modBtn.addActionListener(this);

		Init init = new Init();

		for (int i = 0; i < row; i++) {
			list.add(new Object[] { data[i][0], data[i][1], data[i][2], data[i][3] });
		}

		dtm = new DefaultTableModel(data, title);
		table = new JTable(dtm);
		sp = new JScrollPane(table);
		OnOffBox = new JCheckBox();

		table.getColumn("On/Off").setCellRenderer(dtcr1);
		table.getColumn("On/Off").setCellEditor(new DefaultCellEditor(OnOffBox));
		table.getColumn("On/Off").setPreferredWidth(20);
		;
		OnOffBox.setHorizontalAlignment(JLabel.CENTER);

		DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();
		dtcr2.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumn("날짜").setCellRenderer(dtcr2);
		table.getColumn("시간").setCellRenderer(dtcr2);
		table.getColumn("내용").setCellRenderer(dtcr2);
		table.getColumn("On/Off").setCellRenderer(dtcr2);

		this.setLayout(new FlowLayout());
		getContentPane().add(addBtn);
		getContentPane().add(modBtn);
		getContentPane().add(delBtn);
		getContentPane().add(sp, BorderLayout.CENTER);

		setSize(500, 400);
		setVisible(true);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	DefaultTableCellRenderer dtcr1 = new DefaultTableCellRenderer() {
		public Component getTableCellRendererComponet(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			JCheckBox checkBox = new JCheckBox();
			checkBox.setSelected(((Boolean) value).booleanValue());
			checkBox.setHorizontalAlignment(JLabel.CENTER);
			return checkBox;
		}
	};

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addBtn) {
			dial = new addDial(e.getActionCommand());
		} else if (e.getSource() == modBtn) {
			for (int j = 0; j < table.getColumnCount(); j++) {
				for (int i = 0; i < table.getColumnCount(); i++) {
					data[j][i] = table.getValueAt(j, i);
				}
			}
			list.clear();
			for (int i = 0; i < row; i++) {
				list.add(new Object[] { data[i][0], data[i][1], data[i][2], data[i][3] });
			}
		} else if (e.getSource() == delBtn) {
			int srow = -1;
			srow = table.getSelectedRow();

			if (srow != -1) {
				dtm.removeRow(srow);
				row--;
				for (int i = srow; i < row; i++) {
					for (int j = 0; j < 4; j++) {
						data[i][j] = data[i + 1][j];
					}
				}
				list.clear();
				for (int i = 0; i < row; i++) {
					list.add(new Object[] { data[i][0], data[i][1], data[i][2], data[i][3] });
				}
			} else {
				JOptionPane.showMessageDialog(this, " 삭제할 행 선택");
			}
		} else if (e.getSource() == ExitItem) {
			System.exit(1);
		} else if (e.getSource() == save) {
			try {
				File file = new File("alramFile");
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

				if (file.isFile() && file.canWrite()) {
					for (int i = 0; i < row; i++) {
						for (int j = 0; j < 4; j++) {
							bufferedWriter.write("" + data[i][j] + "|");
						}
						bufferedWriter.newLine();
					}
					bufferedWriter.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void CreateMenu() {
		menuBar = new JMenuBar();
		menu = new JMenu("파일");
		menuBar.add(menu);
		save = new JMenuItem("저장");
		ExitItem = new JMenuItem("종료");
		menu.add(save);
		menu.add(ExitItem);
		menuBar.setBorder(BorderFactory.createLineBorder(Color.gray));

		save.addActionListener(this);
		ExitItem.addActionListener(this);

		setJMenuBar(menuBar);
	}

}

class addDial extends JDialog implements ActionListener{
	Alram alram;
	JPanel Pane=new JPanel();
	
	JLabel tempLb = new JLabel("알람 추가");
	JLabel dateLb = new JLabel("날짜: ");
	JLabel timeLb = new JLabel("시간: ");
	JLabel commentLb = new JLabel("내용: ");
	
	JTextField data=new JTextField(10);
	static JTextField time = new JTextField(5);
	JTextArea comment = new JTextArea();
	
	JButton addBtn = new JButton("등록");
	
	public addDial(String str) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
