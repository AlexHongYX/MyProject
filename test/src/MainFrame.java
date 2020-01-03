import java.awt.event.*;

import javax.swing.*;

import java.util.*;

public class MainFrame extends JFrame implements ActionListener
{
	JLabel numLa,nameLa;
	JTextField numTxt,nameTxt;
	JButton numFBt,nameFBt,addBt,delBt,upBt;
	JTable table;
	JScrollPane panel;
	
	public MainFrame()
	{
		this.setSize(800,600);
		this.setTitle("楼宇信息管理");
		this.setLayout(null);
		numLa=new JLabel("房号");
		numLa.setSize(60, 30);
		numLa.setLocation(30,30);
		this.add(numLa);
		numTxt=new JTextField();
		numTxt.setSize(150, 30);
		numTxt.setLocation(90,30);
		this.add(numTxt);
		numFBt=new JButton("房号查找");
		numFBt.setSize(90, 30);
		numFBt.setLocation(280,30);
		numFBt.addActionListener(this);
		this.add(numFBt);
		
		nameLa=new JLabel("姓名");
		nameLa.setSize(60, 30);
		nameLa.setLocation(410,30);
		this.add(nameLa);
		nameTxt=new JTextField();
		nameTxt.setSize(150, 30);
		nameTxt.setLocation(470,30);
		this.add(nameTxt);
		nameFBt=new JButton("姓名查找");
		nameFBt.setSize(90, 30);
		nameFBt.setLocation(660,30);
		nameFBt.addActionListener(this);
		this.add(nameFBt);
		
		addBt=new JButton("增加");
		addBt.setSize(60, 30);
		addBt.setLocation(30,90);
		addBt.addActionListener(this);
		this.add(addBt);
		delBt=new JButton("删除");
		delBt.setSize(60, 30);
		delBt.setLocation(120,90);
		delBt.addActionListener(this);
		this.add(delBt);
		upBt=new JButton("修改");
		upBt.setSize(60, 30);
		upBt.setLocation(210,90);
		upBt.addActionListener(this);
		this.add(upBt);
		
		BuildDAO dao=new BuildDAO();
		ArrayList list=dao.findBuiByBname("");
		initTable(list);
		this.setVisible(true);
	}
	
	public void initTable(ArrayList<BuildInfo> stus)//初始化表格的方法
	{
		if((stus!=null)||(stus.size()!=0))
 {
			if(panel!=null)
			{
				this.remove(panel);
			}
			String[] columnNames = { "房号", "姓名", "性别", "年龄", "地理位置" };
			String[][] values = new String[stus.size()][5];
			for (int i = 0; i < stus.size(); i++) {
                BuildInfo stu = (BuildInfo) stus.get(i);
				values[i][0] = stu.getBno();
				values[i][1] = stu.getBname();
				values[i][2] = stu.getSex();
				values[i][3] = String.valueOf(stu.getAge());
				values[i][4] = stu.getBdept();
			}
			table = new JTable(values, columnNames);
			panel = new JScrollPane(table);
			panel.setSize(750, 400);
			panel.setLocation(20, 150);
			this.add(panel);
		}
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		JButton bt=(JButton)e.getSource();
		if(bt.getText().equals("姓名查找"))
		{
			BuildDAO dao=new BuildDAO();
			ArrayList list=dao.findBuiByBname(nameTxt.getText().trim());
			initTable(list);
		}
		else
		{
			if(bt.getText().equals("房号查找"))
			{
				BuildDAO dao=new BuildDAO();
				ArrayList list=new ArrayList();
				BuildInfo stu=dao.findBuiByBno(numTxt.getText().trim());
				if(stu!=null)
				{
					list.add(stu);
				}
				initTable(list);
			}
			else
			{
				if(bt.getText().equals("删除"))
				{
					if(table.getSelectedRow()==-1)
					{
						JOptionPane.showMessageDialog(this, "请选中要删除的用户");
					}
					else
					{
						BuildDAO dao = new BuildDAO();
						dao.delBuild(table.getValueAt(table.getSelectedRow(),0).toString());
						ArrayList list = dao.findBuiByBname("");
						initTable(list);
					}
				}
				else
				{
					if(bt.getText().equals("修改"))
					{
						if(table.getSelectedRow()==-1)
						{
							JOptionPane.showMessageDialog(this, "请选中要修改的用户");
						}
						else
						{
							int row = table.getSelectedRow();
							String sno = table.getValueAt(row, 0).toString();
							String sname = table.getValueAt(row, 1).toString();
							String sex = table.getValueAt(row, 2).toString();
							int age = Integer.parseInt(table.getValueAt(row, 3)
									.toString());
							String sdept = table.getValueAt(row, 4).toString();
							BuildInfo bui = new BuildInfo(sno, sname, sex,
									age, sdept);
							AddOrUpdateFrame newFrame = new AddOrUpdateFrame(
									"修改", bui, this);
						}
					}
					else
					{
						AddOrUpdateFrame newFrame=new AddOrUpdateFrame("增加",null,this);
					}
				}
			}
		}
	}
}
