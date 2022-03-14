package com.mycompany.app.requirementApp.view.swing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.assertj.swing.annotation.GUITest;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

import com.mycompany.app.requirementApp.controller.RequirementController;
import com.mycompany.app.requirementApp.model.requirements.Requirement;
import com.mycompany.app.requirementApp.model.requirements.RequirementParser;
import com.mycompany.app.requirementApp.repository.RealRequirementRepository;
import com.mycompany.app.requirementApp.repository.RequirementRepository;
import com.mycompany.app.requirementApp.repository.RequirementWithId;

public class RequirementSwingViewIT extends AssertJSwingJUnitTestCase {

	private FrameFixture window;
	private RequirementSwingView requirementSwingView;
	private RequirementController requirementController;
	private RequirementRepository requirementRepository;

	@Override
	protected void onSetUp() {
		requirementRepository = new RealRequirementRepository();
		// explicit empty the database through the repository
		for (RequirementWithId requirement : requirementRepository.findAll()) {
			requirementRepository.delete(requirement.getId());
		}
		GuiActionRunner.execute(() -> {
			requirementSwingView = new RequirementSwingView();
			requirementController = new RequirementController(requirementSwingView, requirementRepository,
					new RequirementParser());
			requirementSwingView.setRequirementController(requirementController);
			return requirementSwingView;
		});
		window = new FrameFixture(robot(), requirementSwingView);
		window.show(); // shows the frame to test
	}

	@Test
	@GUITest
	public void testAllRequirements() {
		// use the repository to add requirements to the database
		Requirement req1 = mock(Requirement.class);
		Requirement req2 = mock(Requirement.class);
		when(req1.toString()).thenReturn("requirement 1");
		when(req2.toString()).thenReturn("requirement 2");
		RequirementWithId requirement1 = new RequirementWithId("1", req1);
		RequirementWithId requirement2 = new RequirementWithId("2", req2);
		requirementRepository.save(requirement1);
		requirementRepository.save(requirement2);
		// use the controller's allRequirements
		GuiActionRunner.execute(() -> requirementController.allRequirements());
		// and verify that the view's list is populated
		assertThat(window.list("requirementList").contents()).containsExactly(requirement1.toString(), requirement2.toString());
	}

	@Test
	@GUITest
	public void testAddButtonSuccess() {
		window.textBox("idTextBox").enterText("1");
		window.textBox("requirementTextBox").enterText("when a then b");
		window.button(JButtonMatcher.withText("Add")).click();
		assertThat(window.list("requirementList").contents()).containsExactly("1 WHEN a THEN b");
	}

	@Test
	@GUITest
	public void testAddButtonError() {
		Requirement req = mock(Requirement.class);
		requirementRepository.save(new RequirementWithId("1", req));
		window.textBox("idTextBox").enterText("1");
		window.textBox("requirementTextBox").enterText("test");
		window.button(JButtonMatcher.withText("Add")).click();
		assertThat(window.list("requirementList").contents()).isEmpty();
		window.label("errorMessageLabel")
				.requireText("Already existing requirement with id 1: " + new RequirementWithId("1", req));
	}

	@Test
	@GUITest
	public void testDeleteButtonSuccess() {
		// use the controller to populate the view's list...
		GuiActionRunner.execute(() -> requirementController.newRequirement("1", "when a AND b then c"));
		// ...with a requirement to select
		window.list("requirementList").selectItem(0);
		window.button(JButtonMatcher.withText("Delete Selected")).click();
		assertThat(window.list("requirementList").contents()).isEmpty();
	}

	@Test
	@GUITest
	public void testDeleteButtonError() {
		// manually add a requirement to the list, which will not be in the db
		Requirement req = mock(Requirement.class);
		RequirementWithId requirement = new RequirementWithId("1", req);
		GuiActionRunner.execute(() -> requirementSwingView.getListRequirementModel().addElement(requirement));
		window.list("requirementList").selectItem(0);
		window.button(JButtonMatcher.withText("Delete Selected")).click();
		assertThat(window.list("requirementList").contents()).containsExactly(requirement.toString());
		window.label("errorMessageLabel").requireText("No existing requirement with id 1: " + requirement);
	}
}
