package com.nlp.textsummarization;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;

import net.sf.classifier4J.summariser.SimpleSummariser;

public class TextSummarizationGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6664386516270755885L;
	private JPanel contentPane;
	private JTextField textField;
	private String mDocument;
    private SimpleSummariser mSummarizer;
	private JTextArea textArea;
	private JLabel lblNotLoaded;
	private JLabel lblNotDone;
	private JButton btnSummarize;
	private JTextArea summaryTextArea;
	private boolean isDocumentLoaded = false;
	private JScrollPane scrollpane;
	private JScrollPane summaryScrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextSummarizationGUI frame = new TextSummarizationGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TextSummarizationGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textArea = new JTextArea();
		//textArea.setColumns(20);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollpane = new JScrollPane(textArea);
		
		mSummarizer = new SimpleSummariser();
		
		lblNotLoaded = new JLabel("Not loaded...");
		
		btnSummarize = new JButton("Summarize");
		
		lblNotDone = new JLabel("Not done...");
		
		summaryTextArea = new JTextArea();
		summaryTextArea.setRows(30);
		//summaryTextArea.setColumns(20);
		//textArea.setSize(new Dimension(500, 5000));
		summaryTextArea.setLineWrap(true);
		summaryTextArea.setWrapStyleWord(true);
		summaryScrollPane = new JScrollPane(summaryTextArea);
		
		//summaryTextArea.setEditable(false);
		
		JButton btnLoadDocument = new JButton("Load document");
		btnLoadDocument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mDocument = textArea.getText();
				
				if (mDocument.length() <= 10)
				{
					lblNotLoaded.setText("Loading Unsuccessful, please enter at least one sentence");
				} // if
				else
				{
					lblNotLoaded.setText("Loading Successful!");
					isDocumentLoaded = true;
				} // else
				
			} // actionPerformed
		});
		
		btnSummarize.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try
				{
					if (!isDocumentLoaded) {
						lblNotDone.setText("First Load Document...");
					} // if
					else {
						int numSentences = Integer.parseInt(textField.getText());

						if (numSentences < 1) {
							lblNotDone
									.setText("Num sentences cannot be 0 or negative!");
						} // if
						else {
							String summary = mSummarizer.summarise(mDocument,
									numSentences);

							summaryTextArea.setText(summary);
							lblNotDone.setText("Successfully Completed");
						}
					} // else
				} // try
				catch(Exception ex)
				{
					lblNotDone.setText("Enter valid number of sentences...");
				} // catch
			} // actionPerformed
		});
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNumberOfSentences = new JLabel("Number of sentences:");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(summaryScrollPane))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(124)
							.addComponent(btnLoadDocument)
							.addGap(18)
							.addComponent(lblNotLoaded))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollpane, GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(10)
									.addComponent(btnSummarize)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblNotDone)
									.addGap(40)
									.addComponent(lblNumberOfSentences)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNotLoaded)
						.addComponent(btnLoadDocument))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSummarize)
						.addComponent(lblNotDone)
						.addComponent(lblNumberOfSentences)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(summaryScrollPane, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
