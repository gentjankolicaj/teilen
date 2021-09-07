package org.teilen_webcam.client.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ClientGuiFrame extends JFrame {

	private final JPanel contentPane;
	private final ClientPanel clientPanel;
	private final InfoPanel infoPanel;
	private final RoomPanel roomPanel;

	/**
	 * Create the frame.
	 */
	public ClientGuiFrame() {
		setTitle("Teilen-client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		infoPanel = new InfoPanel();
		contentPane.add(infoPanel, BorderLayout.NORTH);

		clientPanel = new ClientPanel();
		contentPane.add(clientPanel, BorderLayout.WEST);
		JScrollPane clientScrollPane = new JScrollPane();
		clientPanel.add(clientScrollPane);

		roomPanel = new RoomPanel();
		contentPane.add(roomPanel, BorderLayout.CENTER);

	}

}
