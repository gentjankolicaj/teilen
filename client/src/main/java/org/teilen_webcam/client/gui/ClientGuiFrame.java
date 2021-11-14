package org.teilen_webcam.client.gui;

import org.teilen_webcam.client.engine.ActivityEngine;
import org.teilen_webcam.client.engine.IOEngine;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ClientGuiFrame extends JFrame {

	private final JPanel contentPane;
	private final UserPanel userPanel;
	private final InfoPanel infoPanel;
	private final RoomPanel roomPanel;
	private final AboutPanel aboutPanel;


	/**
	 * @param ioEngine
	 * @param activityEngine
	 */
	public ClientGuiFrame(IOEngine ioEngine, ActivityEngine activityEngine) {
		setTitle("Teilen-client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		infoPanel = new InfoPanel(ioEngine);
		contentPane.add(infoPanel, BorderLayout.NORTH);

		userPanel = new UserPanel();
		contentPane.add(userPanel, BorderLayout.WEST);

		roomPanel = new RoomPanel();
		contentPane.add(roomPanel, BorderLayout.CENTER);

		aboutPanel = new AboutPanel();
		contentPane.add(aboutPanel, BorderLayout.SOUTH);

		this.setVisible(true);
		this.setResizable(false);

		//Set panels to be used by activity engine
		activityEngine.setPanels(userPanel, roomPanel);
	}

}
