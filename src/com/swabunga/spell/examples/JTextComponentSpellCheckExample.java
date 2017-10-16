/*
 * put your module comment here
 * formatted with JxBeauty (c) johann.langhofer@nextra.at
 */

package com.swabunga.spell.examples;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
import com.swabunga.spell.swing.*;
import com.swabunga.spell.engine.*;
import java.io.*;

/**
 * This class shows an example of how to use the spell checking capability on a
 * JTextComponent.
 *
 * @author Robert Gustavsson (robert@lindesign.se)
 */
public class JTextComponentSpellCheckExample extends JFrame {
	private static final String dictionaryFile = "C:\\Users\\administradorcito\\Documents\\JazzySrc0-2-1\\dict\\corpusMobyDick.txt";
	// private static final String phoneticFile = "swedish_phonet.dat";
	private static final String phoneticFile = "C:\\Users\\administradorcito\\Documents\\JazzySrc0-2-1\\dict\\corpusMobyDick.txt";
	private SpellDictionary dictionary;
	JTextComponent text = null;
	JButton spell = null;

	public JTextComponentSpellCheckExample() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {

			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
		initGUI();
		pack();
	}

	private void initGUI() {
		Container frame = getContentPane();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		frame.setLayout(gridbag);
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 1.0;
		c.weighty = 1.0;
		text = new JTextArea(10, 40);
		addToFrame(frame, text, gridbag, c, 0, 0, 1, 1);
		spell = new JButton("spell");
		spell.addActionListener(new ButtonListener());
		addToFrame(frame, spell, gridbag, c, 0, 1, 1, 1);
	}

	// Helps build gridbaglayout.
	private void addToFrame(Container f, Component c, GridBagLayout gbl, GridBagConstraints gbc, int x, int y, int w,
			int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbl.setConstraints(c, gbc);
		f.add(c);
	}

	public static void main(String[] args) {
		JTextComponentSpellCheckExample d = new JTextComponentSpellCheckExample();
		d.show();
	}

	// INNER CLASSES
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Thread t = new SpellThread();
			t.start();
		}
	}

	private class SpellThread extends Thread {

		public void run() {
			try {
				JTextComponentSpellChecker sc = new JTextComponentSpellChecker(dictionary);
				sc.spellCheck(text);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
