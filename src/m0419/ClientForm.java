package m0419;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * �ͻ���
 * 
 * @author �º���
 * @version 1.0 2016-5-13
 */
public class ClientForm extends JFrame implements ActionListener {
    private JTextField tfdUserName;
    private JList<String> list;
    private DefaultListModel<String> lm;
    private JTextArea allMsg;
    private JTextField tfdMsg;
    private JButton btnCon;
    private JButton btnExit;
    private JButton btnSend;

    // private static String HOST="192.168.31.168";
    private static String HOST = "127.0.0.1";// �Լ����ӣ���������ip��ַ
    private static int PORT = 9090;// �������Ķ˿ں�
    private Socket clientSocket;
    private PrintWriter pw;

    public ClientForm() {

        super("��ʱͨѶ����1.0");
        // �˵���
        addJMenu();

        // ��������
        JPanel p = new JPanel();
        JLabel jlb1 = new JLabel("�û���ʶ:");
        tfdUserName = new JTextField(10);
        // tfdUserName.setEnabled(false);//����ѡ�к��޸�
        // dtfdUserName.setEditable(false);//�����޸�

        // ���Ӱ�ť
        ImageIcon icon = new ImageIcon("a.png");
        btnCon = new JButton("", icon);
        btnCon.setActionCommand("c");
        btnCon.addActionListener(this);

        // �˳���ť
        icon = new ImageIcon("b.jpg");
        btnExit = new JButton("", icon);
        btnExit.setActionCommand("exit");

        btnExit.addActionListener(this);
        btnExit.setEnabled(false);
        p.add(jlb1);
        p.add(tfdUserName);
        p.add(btnCon);
        p.add(btnExit);
        getContentPane().add(p, BorderLayout.NORTH);

        // �м�����
        JPanel cenP = new JPanel(new BorderLayout());
        this.getContentPane().add(cenP, BorderLayout.CENTER);

        // �����б�
        lm = new DefaultListModel<String>();
        list = new JList<String>(lm);
        lm.addElement("ȫ��");
        list.setSelectedIndex(0);// ����Ĭ����ʾ
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// ֻ��ѡ��һ��
        list.setVisibleRowCount(2);
        JScrollPane js = new JScrollPane(list);
        Border border = new TitledBorder("����");
        js.setBorder(border);
        Dimension preferredSize = new Dimension(70, cenP.getHeight());
        js.setPreferredSize(preferredSize);
        cenP.add(js, BorderLayout.EAST);

        // ������Ϣ��
        allMsg = new JTextArea();
        allMsg.setEditable(false);
        cenP.add(new JScrollPane(allMsg), BorderLayout.CENTER);

        // ��Ϣ�������
        JPanel p3 = new JPanel();
        JLabel jlb2 = new JLabel("��Ϣ:");
        p3.add(jlb2);
        tfdMsg = new JTextField(20);
        p3.add(tfdMsg);
        btnSend = new JButton("����");
        btnSend.setEnabled(false);
        btnSend.setActionCommand("send");
        btnSend.addActionListener(this);
        p3.add(btnSend);
        this.getContentPane().add(p3, BorderLayout.SOUTH);

        // *************************************************
        // ���Ͻǵ�X-�رհ�ť-����¼�����
        addWindowListener(new WindowAdapter() {
            // ������
            @Override
            public void windowClosing(WindowEvent e) {
                if (pw == null) {
                    System.exit(0);
                }
                String msg = "exit@#@#ȫ��@#@#null@#@#" + tfdUserName.getText();
                pw.println(msg);
                pw.flush();
                System.exit(0);
            }
        });

        setBounds(300, 300, 400, 300);
        setVisible(true);
    }

    private void addJMenu() {
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu menu = new JMenu("ѡ��");
        menuBar.add(menu);

        JMenuItem menuItemSet = new JMenuItem("����");
        JMenuItem menuItemHelp = new JMenuItem("����");
        menu.add(menuItemSet);
        menu.add(menuItemHelp);

        menuItemSet.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                final JDialog dlg = new JDialog(ClientForm.this);// ����һ������
                // ����ֱ����this

                dlg.setBounds(ClientForm.this.getX()+20, ClientForm.this.getY()+30,
                        350, 150);
                dlg.setLayout(new FlowLayout());
                dlg.add(new JLabel("������IP�Ͷ˿�:"));

                final JTextField tfdHost = new JTextField(10);
                tfdHost.setText(ClientForm.HOST);
                dlg.add(tfdHost);

                dlg.add(new JLabel(":"));

                final JTextField tfdPort = new JTextField(5);
                tfdPort.setText(""+ClientForm.PORT);
                dlg.add(tfdPort);

                JButton btnSet = new JButton("����");
                dlg.add(btnSet);
                btnSet.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String ip = tfdHost.getText();//�������ж�ip�Ƿ�Ϸ�
                        String strs[] = ip.split("\\.");
                        if(strs==null||strs.length!=4){
                            JOptionPane.showMessageDialog(ClientForm.this, "IP��������");
                            return ;
                        }
                        try {
                            for(int i=0;i<4;i++){
                                int num = Integer.parseInt(strs[i]);
                                if(num>255||num<0){
                                    JOptionPane.showMessageDialog(ClientForm.this, "IP��������");
                                    return ;
                                }
                            }
                        } catch (NumberFormatException e2) {
                            JOptionPane.showMessageDialog(ClientForm.this, "IP��������");
                            return ;
                        }

                        ClientForm.HOST=tfdHost.getText();//�Ƚ������ж�ip�Ƿ�Ϸ�

                        try {
                            int port = Integer.parseInt( tfdPort.getText() );
                            if(port<0||port>65535){
                                JOptionPane.showMessageDialog(ClientForm.this, "�˿ڷ�Χ����");
                                return ;
                            }
                        } catch (NumberFormatException e1) {
                            JOptionPane.showMessageDialog(ClientForm.this, "�˿���������");
                            return ;
                        }
                        ClientForm.PORT=Integer.parseInt( tfdPort.getText() );

                        dlg.dispose();//�ر��������
                    }
                });
                dlg.setVisible(true);//��ʾ����
            }
        });

        menuItemHelp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dlg = new JDialog(ClientForm.this);

                dlg.setBounds(ClientForm.this.getX()+30,ClientForm.this.getY()+30, 400, 100);
                dlg.setLayout(new FlowLayout());
                dlg.add(new JLabel("�汾����@�º���.2016.5.16 �ҵ���ҳ:http://chenhaoxiang.github.io"));
                dlg.setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("c")) {
            if (tfdUserName.getText() == null
                    || tfdUserName.getText().trim().length() == 0
                    || "@#@#".equals(tfdUserName.getText())
                    || "@#".equals(tfdUserName.getText())) {
                JOptionPane.showMessageDialog(this, "�û��������������������룡");
                return;
            }

            connecting();// ���ӷ������Ķ���
            if (pw == null) {
                JOptionPane.showMessageDialog(this, "������δ����������δ���ӣ��޷����ӣ�");
                return;
            }

            ((JButton) (e.getSource())).setEnabled(false);
            // ���btnCon��ť--���Դ
            // �൱��btnCon.setEnabled(false);
            btnExit.setEnabled(true);
            btnSend.setEnabled(true);
            tfdUserName.setEditable(false);
        } else if (e.getActionCommand().equals("send")) {
            if (tfdMsg.getText() == null
                    || tfdMsg.getText().trim().length() == 0) {
                return;
            }
            String msg = "on@#@#" + list.getSelectedValue() + "@#@#"
                    + tfdMsg.getText() + "@#@#" + tfdUserName.getText();
            pw.println(msg);
            pw.flush();

            // ��������Ϣ���ı���Ϊ��
            tfdMsg.setText("");
        } else if (e.getActionCommand().equals("exit")) {
            //�Ȱ��Լ����ߵĲ˵����
            lm.removeAllElements();

            sendExitMsg();
            btnCon.setEnabled(true);
            btnExit.setEnabled(false);
            tfdUserName.setEditable(true);
        }

    }

    // ������������˳���Ϣ
    private void sendExitMsg() {
        String msg = "exit@#@#ȫ��@#@#null@#@#" + tfdUserName.getText();
        System.out.println("�˳�:" + msg);
        pw.println(msg);
        pw.flush();
    }

    private void connecting() {
        try {
            // �ȸ����û�������
            String userName = tfdUserName.getText();
            if (userName == null || userName.trim().length() == 0) {
                JOptionPane.showMessageDialog(this, "���ӷ�����ʧ��!\r\n�û����������������룡");
                return;
            }

            clientSocket = new Socket(HOST, PORT);// ������������
            pw = new PrintWriter(clientSocket.getOutputStream(), true);// �����Զ�ˢ��
            pw.println(userName);// ������������Լ����û���
            this.setTitle("�û�[ " + userName + " ]����...");

            new ClientThread().start();// ���ܷ�������������Ϣ---һֱ���ŵ�
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ClientThread extends Thread {
        @Override
        public void run() {
            try {
                Scanner sc = new Scanner(clientSocket.getInputStream());
                while (sc.hasNextLine()) {
                    String str = sc.nextLine();
                    String msgs[] = str.split("@#@#");
                    System.out.println(tfdUserName.getText() + ": " + str);
                    if ("msg".equals(msgs[0])) {
                        if ("server".equals(msgs[1])) {// ���������͵Ĺٷ���Ϣ
                            str = "[ ֪ͨ ]:" + msgs[2];
                        } else {// ������ת����������Ϣ
                            str = "[ " + msgs[1] + " ]˵: " + msgs[2];
                        }
                        allMsg.append("\r\n" + str);
                    }
                    if ("cmdAdd".equals(msgs[0])) {
                        boolean eq = false;
                        for (int i = 0; i < lm.getSize(); i++) {
                            if (lm.getElementAt(i).equals(msgs[2])) {
                                eq = true;
                            }
                        }
                        if (!eq) {
                            lm.addElement(msgs[2]);// �û�����--���
                        }
                    }
                    if ("cmdRed".equals(msgs[0])) {
                        lm.removeElement(msgs[2]);// �û�������--�Ƴ�
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);// ����װ��
        new ClientForm();
    }
}
