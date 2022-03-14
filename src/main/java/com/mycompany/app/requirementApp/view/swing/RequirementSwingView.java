package com.mycompany.app.requirementApp.view.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import com.mycompany.app.requirementApp.controller.RequirementController;
import com.mycompany.app.requirementApp.repository.RequirementWithId;
import com.mycompany.app.requirementApp.view.RequirementView;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class RequirementSwingView extends JFrame implements RequirementView {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JLabel lblRequirement;
	private JTextField txtRequirement;
	private JButton btnAddRequirement;
	private JList<RequirementWithId> listRequirements;
	private JList<String> listTestCases;
	private DefaultListModel<RequirementWithId> listRequirementModel;
	private DefaultListModel<String> listTestCaseModel;

	private RequirementController requirementController;

	public void setRequirementController(RequirementController requirementController) {
		this.requirementController = requirementController;
	}

	DefaultListModel<RequirementWithId> getListRequirementModel() {
		return listRequirementModel;
	}

	DefaultListModel<String> getListTestCaseModel() {
		return listTestCaseModel;
	}

	private JButton btnDeleteRequirement;
	private JLabel lblErrorMessageLabel;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JButton btnGenerateTestCases;
	private JTextArea textReqImportArea;
	private JLabel lblReqImport;
	private JButton btnReqImport;
	private JLabel lblReqImportMessage;
	private JScrollPane scrollPane_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RequirementSwingView frame = new RequirementSwingView();
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
	public RequirementSwingView() {
		setTitle("Requirement App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 70, 100, 70, 70, 100, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 62, 0, 0, 154, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		// LABELS
		JLabel lblId = new JLabel("id");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.anchor = GridBagConstraints.EAST;
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 0;
		contentPane.add(lblId, gbc_lblId);

		lblRequirement = new JLabel("requirement");
		GridBagConstraints gbc_lblRequirement = new GridBagConstraints();
		gbc_lblRequirement.anchor = GridBagConstraints.EAST;
		gbc_lblRequirement.insets = new Insets(0, 0, 5, 5);
		gbc_lblRequirement.gridx = 0;
		gbc_lblRequirement.gridy = 1;
		contentPane.add(lblRequirement, gbc_lblRequirement);

		lblReqImport = new JLabel("requirements import");
		GridBagConstraints gbc_lblReqImport = new GridBagConstraints();
		gbc_lblReqImport.insets = new Insets(0, 0, 5, 5);
		gbc_lblReqImport.gridx = 0;
		gbc_lblReqImport.gridy = 3;
		contentPane.add(lblReqImport, gbc_lblReqImport);

		lblReqImportMessage = new JLabel(" ");
		lblReqImportMessage.setName("importMessageLabel");
		GridBagConstraints gbc_lblReqImportMessage = new GridBagConstraints();
		gbc_lblReqImportMessage.gridwidth = 5;
		gbc_lblReqImportMessage.insets = new Insets(0, 0, 5, 5);
		gbc_lblReqImportMessage.gridx = 0;
		gbc_lblReqImportMessage.gridy = 5;
		contentPane.add(lblReqImportMessage, gbc_lblReqImportMessage);

		lblErrorMessageLabel = new JLabel(" ");
		lblErrorMessageLabel.setName("errorMessageLabel");
		GridBagConstraints gbc_lblErrorMessageLabel = new GridBagConstraints();
		gbc_lblErrorMessageLabel.gridwidth = 5;
		gbc_lblErrorMessageLabel.gridx = 0;
		gbc_lblErrorMessageLabel.gridy = 8;
		contentPane.add(lblErrorMessageLabel, gbc_lblErrorMessageLabel);

		// BUTTON ENABLER
		
		CaretListener listener = new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				btnReqImport.setEnabled(!textReqImportArea.getText().trim().isEmpty());
			}
		};

		KeyAdapter btnAddEnabler = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				btnAddRequirement
						.setEnabled(!txtId.getText().trim().isEmpty() && !txtRequirement.getText().trim().isEmpty());
			}
		};

		ListDataListener btnGenerateEnabler = new ListDataListener() {
			private void enableIfNeeded() {
				if (listRequirementModel.isEmpty()) {
					btnGenerateTestCases.setEnabled(false);
				} else {
					btnGenerateTestCases.setEnabled(true);
				}
			}

			public void contentsChanged(ListDataEvent e) {
				enableIfNeeded();
			}

			@Override
			public void intervalAdded(ListDataEvent e) {
				enableIfNeeded();
			}

			@Override
			public void intervalRemoved(ListDataEvent e) {
				enableIfNeeded();
			}
		};

		// TEXT FIELDS

		// ID FIELD
		txtId = new JTextField();
		txtId.addKeyListener(btnAddEnabler);
		txtId.setName("idTextBox");
		GridBagConstraints gbc_txtId = new GridBagConstraints();
		gbc_txtId.gridwidth = 4;
		gbc_txtId.insets = new Insets(0, 0, 5, 0);
		gbc_txtId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtId.gridx = 1;
		gbc_txtId.gridy = 0;
		txtId.setColumns(10);
		contentPane.add(txtId, gbc_txtId);

		// REQUIREMENT FIELD
		txtRequirement = new JTextField();
		txtRequirement.addKeyListener(btnAddEnabler);
		txtRequirement.setName("requirementTextBox");
		GridBagConstraints gbc_textRequirement = new GridBagConstraints();
		gbc_textRequirement.insets = new Insets(0, 0, 5, 0);
		gbc_textRequirement.gridwidth = 4;
		gbc_textRequirement.fill = GridBagConstraints.HORIZONTAL;
		gbc_textRequirement.gridx = 1;
		gbc_textRequirement.gridy = 1;
		contentPane.add(txtRequirement, gbc_textRequirement);
		txtRequirement.setColumns(10);

		// BUTTONS

		// ADD BUTTON
		btnAddRequirement = new JButton("Add");
		btnAddRequirement.setEnabled(false);
		btnAddRequirement.addActionListener(
				e -> requirementController.newRequirement(txtId.getText(), txtRequirement.getText()));
		GridBagConstraints gbc_btnAddRequirement = new GridBagConstraints();
		gbc_btnAddRequirement.gridwidth = 2;
		gbc_btnAddRequirement.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddRequirement.gridx = 2;
		gbc_btnAddRequirement.gridy = 2;
		contentPane.add(btnAddRequirement, gbc_btnAddRequirement);

		// DELETE BUTTON
		btnDeleteRequirement = new JButton("Delete Selected");
		btnDeleteRequirement.setEnabled(false);
		btnDeleteRequirement
				.addActionListener(e -> requirementController.deleteRequirement(listRequirements.getSelectedValue()));
		GridBagConstraints gbc_btnDeleteRequirement = new GridBagConstraints();
		gbc_btnDeleteRequirement.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteRequirement.gridx = 1;
		gbc_btnDeleteRequirement.gridy = 7;
		contentPane.add(btnDeleteRequirement, gbc_btnDeleteRequirement);

		// GENERATE BUTTON
		btnGenerateTestCases = new JButton("Generate Test Cases");
		btnGenerateTestCases.setEnabled(false);
		btnGenerateTestCases.addActionListener(e -> {
			List<RequirementWithId> reqList = new ArrayList<RequirementWithId>();
			for (int i = 0; i < listRequirementModel.getSize(); i++) {
				reqList.add(listRequirementModel.get(i));
			}
			requirementController.generateTestCases(reqList);
		});
		GridBagConstraints gbc_btnGenerateTestCases = new GridBagConstraints();
		gbc_btnGenerateTestCases.insets = new Insets(0, 0, 5, 0);
		gbc_btnGenerateTestCases.gridx = 4;
		gbc_btnGenerateTestCases.gridy = 7;
		contentPane.add(btnGenerateTestCases, gbc_btnGenerateTestCases);

		listRequirementModel = new DefaultListModel<>();
		listRequirementModel.addListDataListener(btnGenerateEnabler);

		// IMPORT BUTTON
		btnReqImport = new JButton("Import");
		btnReqImport.setEnabled(false);
		btnReqImport.addActionListener(e ->{
			requirementController.importRequirements(textReqImportArea.getText());
		});
		GridBagConstraints gbc_btnReqmport = new GridBagConstraints();
		gbc_btnReqmport.gridwidth = 2;
		gbc_btnReqmport.insets = new Insets(0, 0, 5, 5);
		gbc_btnReqmport.gridx = 2;
		gbc_btnReqmport.gridy = 4;
		contentPane.add(btnReqImport, gbc_btnReqmport);

		// TEXT AREA
		
		// IMPORT AREA
		scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridwidth = 4;
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_2.gridx = 1;
		gbc_scrollPane_2.gridy = 3;
		contentPane.add(scrollPane_2, gbc_scrollPane_2);
		
		textReqImportArea = new JTextArea();
		//textReqImportArea.addKeyListener(btnImportEnabler);
		textReqImportArea.addCaretListener(listener);
		textReqImportArea.setName("reqImportBox");
		scrollPane_2.setViewportView(textReqImportArea);

		// REQUIREMENT AREA
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 6;
		contentPane.add(scrollPane, gbc_scrollPane);
		listRequirements = new JList<>(listRequirementModel);
		scrollPane.setViewportView(listRequirements);
		listRequirements.addListSelectionListener(
				e -> btnDeleteRequirement.setEnabled(listRequirements.getSelectedIndex() != -1));
		listRequirements.setName("requirementList");

		listTestCaseModel = new DefaultListModel<>();

		// TEST CASE AREA
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.gridx = 3;
		gbc_scrollPane_1.gridy = 6;
		contentPane.add(scrollPane_1, gbc_scrollPane_1);
		listTestCases = new JList<>(listTestCaseModel);
		scrollPane_1.setViewportView(listTestCases);
		listTestCases.setName("testCaseList");
	}

	public void showAllRequirements(List<RequirementWithId> requirements) {
		requirements.stream().forEach(listRequirementModel::addElement);
	}

	public void generateTestCases(List<RequirementWithId> requirements) {
		listTestCaseModel.clear();
		requirements.stream().forEach((req) -> {
			for (int i = 0; i < req.getRequirement().requirementTestCase().size(); i++) {
				listTestCaseModel.addElement(
						req.getId() + "_tc_" + (i + 1) + " " + req.getRequirement().requirementTestCase().get(i));
			}
		});
		resetErrorLabel();
	}

	@Override
	public void showError(String message) {
		lblErrorMessageLabel.setText(message);
	}
	
	@Override
	public void showImportError(String message) {
		lblReqImportMessage.setText(message);
	}

	@Override
	public void requirementAdded(RequirementWithId requirement) {
		listRequirementModel.addElement(requirement);
		resetErrorLabel();
	}

	@Override
	public void requirementRemoved(RequirementWithId requirement) {
		listRequirementModel.removeElement(requirement);
		resetErrorLabel();
	}

	private void resetErrorLabel() {
		lblErrorMessageLabel.setText(" ");
	}
	
	private void resetImportLabel() {
		lblReqImportMessage.setText(" ");
	}

	@Override
	public void importRequirements(List<RequirementWithId> listRequirementToImport) {
		listRequirementToImport.stream().forEach(listRequirementModel::addElement);
		textReqImportArea.setText("");
		resetImportLabel();
	}

}
