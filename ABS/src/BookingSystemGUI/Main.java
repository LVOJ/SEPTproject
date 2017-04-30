package BookingSystemGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
//					frame.setVisible(true);
					frame.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		//JDialog dialog = new JDialog(new JFrame(), "No min max buttons");
		JFrame dialog = new JFrame();
//		dialog.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent evt) {
//                System.exit(0);
//            }
//        });
		dialog.setResizable(false);
		dialog.setTitle("Appointment Booking System");
		
		contentPane = new JPanel();
		
		dialog.setVisible(true);
		dialog.setSize(250, 110);
		
		dialog.setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialog.setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Login().setVisible(true);
				dialog.dispose();
			}
		});
		btnLogin.setBounds(25, 25, 85, 30);
		contentPane.add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Register().setVisible(true);
				dialog.dispose();
			}
		});
		btnRegister.setBounds(135, 25, 85, 30);
		contentPane.add(btnRegister);
		
//		JButton btnTerminate = new JButton("Close");
//		btnTerminate.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				System.exit(0);
//			}
//		});
//		btnTerminate.setBounds(145, 60, 75, 23);
//		contentPane.add(btnTerminate);
	
		  dispose();  
	}
}
