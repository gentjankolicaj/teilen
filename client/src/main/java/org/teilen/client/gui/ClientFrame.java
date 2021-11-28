package org.teilen.client.gui;

import org.teilen.client.engine.ActivityEngine;
import org.teilen.client.engine.IOEngine;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ClientFrame extends JFrame {
	private final JPanel contentPane;
	private final InfoPanel infoPanel;
	private final ActivityPanel activityPanel;
	private final AboutPanel aboutPanel;


	/**
	 * @param ioEngine
	 * @param activityEngine
	 */
	public ClientFrame(IOEngine ioEngine, ActivityEngine activityEngine) {
		this.setTitle("Teilen-CLIENT");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		infoPanel = new InfoPanel(ioEngine);
		contentPane.add(infoPanel, BorderLayout.NORTH);

		activityPanel = new ActivityPanel();
		contentPane.add(activityPanel, BorderLayout.CENTER);

		aboutPanel = new AboutPanel();
		contentPane.add(aboutPanel, BorderLayout.SOUTH);

		this.setVisible(true);
		this.setResizable(true);

		//Set panels to be used by activity engine
		activityEngine.setActivityPanel(activityPanel);
	}

}
