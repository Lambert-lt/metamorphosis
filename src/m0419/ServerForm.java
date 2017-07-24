package m0419;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * ������
 * 
 * @author �º���
 * 
 * @version 1.0 2016-5-13
 */
public class ServerForm extends JFrame {
    private JList<String> list;
    private JTextArea area;
    private DefaultListModel<String> lm;

    public ServerForm() {
        JPanel p = new JPanel(new BorderLayout());
        // ���ұߵ��û������б�
        lm = new DefaultListModel<String>();
        list = new JList<String>(lm);
        JScrollPane js = new JScrollPane(list);
        Border border = new TitledBorder("����");
        js.setBorder(border);
        Dimension d = new Dimension(100, p.getHeight());
        js.setPreferredSize(d);// ����λ��
        p.add(js, BorderLayout.EAST);

        // ֪ͨ�ı�����
        area = new JTextArea();
        //area.setEnabled(false);//����ѡ�к��޸�
        area.setEditable(false);
        p.add(new JScrollPane(area), BorderLayout.CENTER);
        this.getContentPane().add(p);

        // ��Ӳ˵���
        JMenuBar bar = new JMenuBar();// �˵���
        this.setJMenuBar(bar);
        JMenu jm = new JMenu("����(C)");
        jm.setMnemonic('C');// �������Ƿ�---Alt+'C'����ʾ��������������
        bar.add(jm);
        final JMenuItem jmi1 = new JMenuItem("����");
        jmi1.setAccelerator(KeyStroke.getKeyStroke('R', KeyEvent.CTRL_MASK));// ���ÿ�ݼ�Ctrl+'R'
        jmi1.setActionCommand("run");
        jm.add(jmi1);

        JMenuItem jmi2 = new JMenuItem("�˳�");
        jmi2.setAccelerator(KeyStroke.getKeyStroke('E', KeyEvent.CTRL_MASK));// ���ÿ�ݼ�Ctrl+'R'
        jmi2.setActionCommand("exit");
        jm.add(jmi2);


        // ����
        ActionListener a1 = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("run")) {
                    startServer();
                    jmi1.setEnabled(false);// �ڲ�����~���ʵ�ֻ����final����
                } else {
                    System.exit(0);
                }
            }
        };

        jmi1.addActionListener(a1);

        Toolkit tk = Toolkit.getDefaultToolkit();
        int width = (int) tk.getScreenSize().getWidth();
        int height = (int) tk.getScreenSize().getHeight();
        this.setBounds(width / 4, height / 4, width / 2, height / 2);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);// �رհ�ť������

        setVisible(true);
    }

    private static final int PORT = 9090;

    protected void startServer() {
        try {
            ServerSocket server = new ServerSocket(PORT);
            area.append("��������" + server);
            new ServerThread(server).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // �����������������û������ֺ�Socket----��
    private Map<String, Socket> usersMap = new HashMap<String, Socket>();

    class ServerThread extends Thread {
        private ServerSocket server;

        public ServerThread(ServerSocket server) {
            this.server = server;
        }

        @Override
        public void run() {
            try {// �Ϳͻ�������
                while (true) {
                    Socket socketClient = server.accept();
                    Scanner sc = new Scanner(socketClient.getInputStream());
                    if (sc.hasNext()) {
                        String userName = sc.nextLine();
                        area.append("\r\n�û�[ " + userName + " ]��¼ " + socketClient);// �ڿͻ���֪ͨ
                        lm.addElement(userName);// ��ӵ��û������б�

                        new ClientThread(socketClient).start();// ר��Ϊ����ͻ��˷���

                        usersMap.put(userName, socketClient);// �ѵ�ǰ��¼���û��ӵ��������û�������

                        msgAll(userName);// �ѡ���ǰ�û���¼����Ϣ���û�����֪ͨ�����������Ѿ����ߵ���
                        msgSelf(socketClient);// ֪ͨ��ǰ��¼���û����й����������˵���Ϣ

                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    class ClientThread extends Thread {
        private Socket socketClient;

        public ClientThread(Socket socketClient) {
            this.socketClient = socketClient;
        }

        @Override
        public void run() {
            System.out.println("һ����ͻ���ͨѶ���߳���������ʼͨѶ...");
            try {
                Scanner sc = new Scanner(socketClient.getInputStream());
                while (sc.hasNext()) {
                    String msg = sc.nextLine();
                    System.out.println(msg);
                    String msgs[] = msg.split("@#@#");
                    //����
                    if(msgs.length!=4){
                        System.out.println("���ڴ���...");
                        continue;
                    }

                    if("on".equals(msgs[0])){
                        sendMsgToSb(msgs);
                    }

                    if("exit".equals(msgs[0])){
                        //��������ʾ
                        area.append("\r\n�û�[ " + msgs[3] + " ]���˳�!" + usersMap.get(msgs[3]));

                        //�������û����аѸ��û�ɾ��
                        usersMap.remove(msgs[3]);

                        //�������������б��аѸ��û�ɾ��
                        lm.removeElement(msgs[3]);

                        //֪ͨ�����û������û��Ѿ��˳�
                        sendExitMsgToAll(msgs);
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    //֪ͨ�����û������û��Ѿ��˳�
    private void sendExitMsgToAll(String[] msgs) throws IOException {
        Iterator<String> userNames = usersMap.keySet().iterator();

        while(userNames.hasNext()){
            String userName = userNames.next();
            Socket s = usersMap.get(userName);
            PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
            String str = "msg@#@#server@#@#�û�[ "+msgs[3]+" ]���˳���";
            pw.println(str);
            pw.flush();

            str = "cmdRed@#@#server@#@#"+msgs[3];
            pw.println(str);
            pw.flush();
        }

    }


    //�������ѿͻ��˵�������Ϣת������Ӧ�������ͻ���
    public void sendMsgToSb(String[] msgs) throws IOException {

        if("ȫ��".equals(msgs[1])){
            Iterator<String> userNames = usersMap.keySet().iterator();
            //����ÿһ�������û�����������Ϣ������
            while(userNames.hasNext()){
                String userName = userNames.next();
                Socket s = usersMap.get(userName);
                PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
                String str = "msg@#@#"+msgs[3]+"@#@#"+msgs[2];
                pw.println(str);
                pw.flush();
            }
        }else{
            Socket s = usersMap.get(msgs[1]);
            PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
            String str = "msg@#@#"+msgs[3]+"����@#@#"+msgs[2];
            pw.println(str);
            pw.flush();
        }
    }



    /**
     * �ѡ���ǰ�û���¼����Ϣ���û�����֪ͨ�����������Ѿ����ߵ���
     * 
     * @param userName
     */
    // ����˼·:�ӳ������ΰ�ÿ��socket(����ÿ�������û�)ȡ������������userName
    public void msgAll(String userName) {
        Iterator<Socket> it = usersMap.values().iterator();
        while (it.hasNext()) {
            Socket s = it.next();
            try {
                PrintWriter pw = new PrintWriter(s.getOutputStream(), true);// ��trueΪ�Զ�ˢ��
                String msg = "msg@#@#server@#@#�û�[ " + userName + " ]�ѵ�¼!";// ֪ͨ�ͻ�����ʾ��Ϣ
                pw.println(msg);
                pw.flush();
                msg = "cmdAdd@#@#server@#@#" + userName;// ֪ͨ�ͻ����������б�����û����ߡ�
                pw.println(msg);
                pw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * ֪ͨ��ǰ��¼���û����й����������˵���Ϣ
     * 
     * @param socketClient
     */
    // ��ԭ���Ѿ����ߵ���Щ�û������ַ����õ�¼�û����������Լ������е�lm�����Ӧ���û���
    public void msgSelf(Socket socketClient) {
        try {
            PrintWriter pw = new PrintWriter(socketClient.getOutputStream(),true);
            Iterator<String> it = usersMap.keySet().iterator();
            while (it.hasNext()) {
                String msg = "cmdAdd@#@#server@#@#" + it.next();
                pw.println(msg);
                pw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);// ����װ��
        new ServerForm();
    }
}