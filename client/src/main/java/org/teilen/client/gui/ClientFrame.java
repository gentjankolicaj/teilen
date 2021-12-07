package org.teilen.client.gui;

import org.teilen.client.engine.ActivityEngine;
import org.teilen.client.engine.IOEngine;
import org.teilen.client.global.GlobalConfig;

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
		this.setTitle("Teilen ~ CLIENT");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(GlobalConfig.xBound, GlobalConfig.yBound, GlobalConfig.width, GlobalConfig.height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		this.infoPanel = new InfoPanel(ioEngine);
		contentPane.add(infoPanel, BorderLayout.NORTH);

		this.activityPanel = new ActivityPanel();
		contentPane.add(activityPanel, BorderLayout.CENTER);

		this.aboutPanel = new AboutPanel();
		contentPane.add(aboutPanel, BorderLayout.SOUTH);

		this.setVisible(true);
		this.setResizable(false);
		this.pack();

		//Set panels to be used by activity engine
		activityEngine.setActivityPanel(activityPanel);
		activityEngine.setInfoPanel(infoPanel);
	}

}
