package chat.gpt.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import chat.gpt.domain.listeners.KeyboardListener;
import chat.gpt.domain.listeners.MouseListener;
import chat.gpt.domain.listeners.NotificationListener;
import chat.gpt.domain.table.Table;
import chat.gpt.domain.table.TableCell;

public class MainView extends JFrame {

	private JButton botaoReiniciar;
	private Table table;
	private KeyboardListener listener;
	private NotificationListener gameListener;
	private MouseListener mouseListener;
	
	public MainView(Table table, KeyboardListener listener, NotificationListener gameListener, MouseListener mouseListener) {
		super("Jogo dos Oito");
		this.table = table;
		this.listener = listener;
		this.gameListener = gameListener;
		this.mouseListener = mouseListener;
	}

	public void start() {
		for (TableCell cell : table.getBotoes()) {
			cell.setFont(new Font("Arial", Font.BOLD, 36));
			cell.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
					mouseListener.notify("move", cell);
				}
			});
			add(cell);
		}

		botaoReiniciar = new JButton("Reiniciar");
		botaoReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameListener.notify("restart", "restart");
			}
		});

		add(new JLabel(""));
		add(botaoReiniciar);
		add(new JLabel(""));

		setFocusable(true);
		requestFocus();
		addKeyListener(listener);
		setVisible(true);
	}
}
