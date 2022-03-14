package com.mycompany.app.requirementApp.view.swing;

import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;

import org.assertj.swing.annotation.GUITest;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.core.matcher.JLabelMatcher;
import org.assertj.swing.core.matcher.JTextComponentMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.assertj.swing.junit.runner.GUITestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mycompany.app.requirementApp.controller.RequirementController;
import com.mycompany.app.requirementApp.model.requirements.Requirement;
import com.mycompany.app.requirementApp.repository.RequirementWithId;

@RunWith(GUITestRunner.class)
public class RequirementSwingViewTest extends AssertJSwingJUnitTestCase {

	private FrameFixture window;

	private RequirementSwingView requirementSwingView;

	@Mock
	private RequirementController requirementController;

	private AutoCloseable closeable;

	@Override
	protected void onSetUp() {
		closeable = MockitoAnnotations.openMocks(this);
		GuiActionRunner.execute(() -> {
			requirementSwingView = new RequirementSwingView();
			requirementSwingView.setRequirementController(requirementController);
			return requirementSwingView;
		});
		window = new FrameFixture(robot(), requirementSwingView);
		window.show(); // shows the frame to test
		// window.maximize();
	}

	@Override
	protected void onTearDown() throws Exception {
		closeable.close();
	}

//	@Test
//	public void test() {
//		// just to check the setup works
//	}

	@Test
	@GUITest
	public void testControlsInitialStates() {
		// check "label" and "text box" for id
		window.label(JLabelMatcher.withText("id"));
		window.textBox(JTextComponentMatcher.withName("idTextBox"));
		window.textBox("idTextBox").requireEnabled();
		// check "label" and "text box" for requirement
		window.label(JLabelMatcher.withText("requirement"));
		window.textBox(JTextComponentMatcher.withName("requirementTextBox"));
		window.textBox("requirementTextBox").requireEnabled();
		// check "button" for add a requirement
		window.button(JButtonMatcher.withText("Add")).requireDisabled();
		// check the list of requirement added
		window.list("requirementList");
		// check "button" for delete a requirement
		window.button(JButtonMatcher.withText("Delete Selected")).requireDisabled();
		// check the list of requirement added
		window.list("testCaseList");
		// check "button" for generate the test cases
		window.button(JButtonMatcher.withText("Generate Test Cases")).requireDisabled();
		// check label for errors
		window.label("errorMessageLabel").requireText(" ");
		// check "label" and "text area" for requirements import
		window.label(JLabelMatcher.withText("requirements import"));
		window.textBox(JTextComponentMatcher.withName("reqImportBox"));
		window.textBox("reqImportBox").requireEnabled();
		// check "button" for import requirements
		window.button(JButtonMatcher.withText("Import")).requireDisabled();
		// check label for errors
		window.label("importMessageLabel").requireText(" ");
	}

	// CHECK THAT THERE IS INPUT TEXT IN THE INPUT TEXT AREA -> BUTTON ENABLED
	// ADD
	@Test
	public void testWhenIdAndRequirementAreNonEmptyThenAddButtonShouldBeEnabled() {
		window.textBox("idTextBox").enterText("1");
		window.textBox("requirementTextBox").enterText("test");
		window.button(JButtonMatcher.withText("Add")).requireEnabled();
	}

	// ADD
	@Test
	public void testWhenEitherIdOrRequirementAreBlankThenAddButtonShouldBeDisabled() {
		JTextComponentFixture idTextBox = window.textBox("idTextBox");
		JTextComponentFixture nameTextBox = window.textBox("requirementTextBox");

		idTextBox.enterText("1");
		nameTextBox.enterText(" ");
		window.button(JButtonMatcher.withText("Add")).requireDisabled();

		idTextBox.setText("");
		nameTextBox.setText("");

		idTextBox.enterText(" ");
		nameTextBox.enterText("test");
		window.button(JButtonMatcher.withText("Add")).requireDisabled();
	}

	// IMPORT
	@Test
	public void testWhenRequirementToImportAreNonEmptyThenImportButtonShouldBeEnabled() {
		JTextComponentFixture reqImportBox = window.textBox("reqImportBox");
		reqImportBox.enterText("1");
		window.button(JButtonMatcher.withText("Import")).requireEnabled();
		reqImportBox.setText("");
		window.button(JButtonMatcher.withText("Import")).requireDisabled();
	}

	// DELETE
	@Test
	public void testDeleteButtonShouldBeEnabledOnlyWhenARequirementIsSelected() {
		Requirement mockReq = mock(Requirement.class);
		when(mockReq.toString()).thenReturn("requirement text");
		GuiActionRunner.execute(
				() -> requirementSwingView.getListRequirementModel().addElement(new RequirementWithId("1", mockReq)));
		window.list("requirementList").selectItem(0);
		JButtonFixture deleteButton = window.button(JButtonMatcher.withText("Delete Selected"));
		deleteButton.requireEnabled();
		window.list("requirementList").clearSelection();
		deleteButton.requireDisabled();
	}

	// GENERATE
	@Test
	public void testGenerateButtonShouldBeEnabledOnlyWhenRequirementsArePresent() {
		Requirement mockReq = mock(Requirement.class);
		when(mockReq.toString()).thenReturn("requirement text");
		GuiActionRunner.execute(
				() -> requirementSwingView.getListRequirementModel().addElement(new RequirementWithId("1", mockReq)));
		JButtonFixture generateButton = window.button(JButtonMatcher.withText("Generate Test Cases"));
		generateButton.requireEnabled();
		GuiActionRunner.execute(() -> requirementSwingView.getListRequirementModel().clear());
		generateButton.requireDisabled();
	}

	// TEST THE METHODS
	// SHOW ALL REQUIREMENTS
	@Test
	public void testShowAllRequirementsShouldAddRequirementDescriptionsToTheList() {
		Requirement requirement1 = mock(Requirement.class);
		Requirement requirement2 = mock(Requirement.class);
		when(requirement1.toString()).thenReturn("requirement text 1");
		when(requirement2.toString()).thenReturn("requirement text 2");
		RequirementWithId reqWithId1 = new RequirementWithId("1", requirement1);
		RequirementWithId reqWithId2 = new RequirementWithId("2", requirement2);
		GuiActionRunner.execute(() -> requirementSwingView.showAllRequirements(Arrays.asList(reqWithId1, reqWithId2)));
		String[] listContents = window.list("requirementList").contents();
		assertThat(listContents).containsExactly(reqWithId1.toString(), reqWithId2.toString());
	}

	// SHOW ERRORS
	@Test
	public void testShowErrorShouldShowTheMessageInTheErrorLabel() {
		Requirement req = mock(Requirement.class);
		when(req.toString()).thenReturn("requirement text");
		RequirementWithId requirement = new RequirementWithId("1", req);
		GuiActionRunner.execute(() -> requirementSwingView.showError("error message: " + requirement));
		window.label("errorMessageLabel").requireText("error message: " + requirement);
	}

	// REQUIREMENT ADDED
	@Test
	public void testRequirementAddedShouldAddTheRequirementToTheListAndResetTheErrorLabel() {
		Requirement req1 = mock(Requirement.class);
		RequirementWithId requirement = new RequirementWithId("id_1", req1);
		GuiActionRunner.execute(() -> requirementSwingView.showError(" something "));
		GuiActionRunner.execute(() -> requirementSwingView.requirementAdded(new RequirementWithId("id_1", req1)));
		String[] listContents = window.list("requirementList").contents();
		assertThat(listContents).containsExactly(requirement.toString());
		window.label("errorMessageLabel").requireText(" ");
	}

	// REQUIREMENT REMOVED
	@Test
	public void testRequirementRemovedShouldRemoveTheRequirementFromTheListAndResetTheErrorLabel() {
		// setup
		Requirement req1 = mock(Requirement.class);
		when(req1.toString()).thenReturn("requirement 1");
		Requirement req2 = mock(Requirement.class);

		RequirementWithId requirement1 = new RequirementWithId("id_1", req1);
		RequirementWithId requirement2 = new RequirementWithId("id_2", req2);
		GuiActionRunner.execute(() -> {
			DefaultListModel<RequirementWithId> listRequirementsModel = requirementSwingView.getListRequirementModel();
			listRequirementsModel.addElement(requirement1);
			listRequirementsModel.addElement(requirement2);
		});
		GuiActionRunner.execute(() -> requirementSwingView.showError(" something "));
		// execute
		GuiActionRunner.execute(() -> requirementSwingView.requirementRemoved(new RequirementWithId("id_1", req1)));
		// verify
		String[] listContents = window.list("requirementList").contents();
		assertThat(listContents).containsExactly(requirement2.toString());
		window.label("errorMessageLabel").requireText(" ");
	}

	// GENERATE TEST CASES
	@Test
	public void testGenerateTestCaseShouldGenerateTheTestCaseToTheListAndResetTheErrorLabel() {
		Requirement req1 = mock(Requirement.class);
		RequirementWithId requirement = new RequirementWithId("id_1", req1);
		when(req1.toString()).thenReturn("requirement text");
		when(req1.requirementTestCase()).thenReturn(Arrays.asList("tc_1", "tc_2"));
		GuiActionRunner.execute(() -> requirementSwingView.getListRequirementModel().addElement(requirement));
		GuiActionRunner.execute(() -> requirementSwingView.showError(" something "));
		GuiActionRunner.execute(() -> requirementSwingView.generateTestCases(Arrays.asList(requirement)));
		String[] listContents = window.list("testCaseList").contents();

		List<String> expectedResult = new ArrayList<String>();
		for (int i = 0; i < requirement.getRequirement().requirementTestCase().size(); i++) {
			expectedResult.add(requirement.getId() + "_tc_" + (i + 1) + " "
					+ requirement.getRequirement().requirementTestCase().get(i));
		}

		assertThat(listContents).containsExactlyElementsOf(expectedResult);
		window.label("errorMessageLabel").requireText(" ");
	}

	// IMPORT REQUIREMENTS
	@Test
	public void testImportRequirementsShouldAddRequirementDescriptionsToTheListAndResetImportMessageLabel() {
		Requirement requirement1 = mock(Requirement.class);
		Requirement requirement2 = mock(Requirement.class);
		when(requirement1.toString()).thenReturn("requirement text 1");
		when(requirement2.toString()).thenReturn("requirement text 2");
		RequirementWithId reqWithId1 = new RequirementWithId("1", requirement1);
		RequirementWithId reqWithId2 = new RequirementWithId("2", requirement2);
		GuiActionRunner.execute(() -> requirementSwingView.showImportError(" something "));
		GuiActionRunner.execute(() -> requirementSwingView.importRequirements(Arrays.asList(reqWithId1, reqWithId2)));
		String[] listContents = window.list("requirementList").contents();
		assertThat(listContents).containsExactly(reqWithId1.toString(), reqWithId2.toString());
		window.label("importMessageLabel").requireText(" ");
		window.textBox("reqImportBox").requireText("");
	}

	// DELEGATE THE CONTROLLER
	// ADD BUTTON
	@Test
	public void testAddButtonShouldDelegateToRequirementControllerNewRequirement() {
		window.textBox("idTextBox").enterText("1");
		window.textBox("requirementTextBox").enterText("test");
		window.button(JButtonMatcher.withText("Add")).click();
		verify(requirementController).newRequirement("1", "test");
	}

	// DELETE BUTTON
	@Test
	public void testDeleteButtonShouldDelegateToRequirementControllerDeleteRequirement() {
		Requirement req1 = mock(Requirement.class);
		Requirement req2 = mock(Requirement.class);
		RequirementWithId reqWithId1 = new RequirementWithId("1", req1);
		RequirementWithId reqWithId2 = new RequirementWithId("2", req2);
		GuiActionRunner.execute(() -> {
			DefaultListModel<RequirementWithId> listRequirementsModel = requirementSwingView.getListRequirementModel();
			listRequirementsModel.addElement(reqWithId1);
			listRequirementsModel.addElement(reqWithId2);
		});
		window.list("requirementList").selectItem(1);
		window.button(JButtonMatcher.withText("Delete Selected")).click();
		verify(requirementController).deleteRequirement(reqWithId2);
	}

	// GENERATE BUTTON
	@Test
	public void testGenerateTestCaseButtonShouldDelegateToRequirementControllerGenerateTestCases() {
		Requirement req1 = mock(Requirement.class);
		RequirementWithId requirement = new RequirementWithId("id_1", req1);
		GuiActionRunner.execute(() -> {
			DefaultListModel<RequirementWithId> listRequirementsModel = requirementSwingView.getListRequirementModel();
			listRequirementsModel.addElement(requirement);
		});
		window.button(JButtonMatcher.withText("Generate Test Cases")).click();
		verify(requirementController).generateTestCases(Arrays.asList(requirement));
	}

	// IMPORT BUTTON
	@Test
	public void testImportButtonShouldDelegateToRequirementControllerNewRequirement() {
		window.textBox("reqImportBox").enterText("1");
		window.button(JButtonMatcher.withText("Import")).click();
		verify(requirementController).importRequirements("1");
	}

}