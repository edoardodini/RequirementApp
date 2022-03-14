package com.mycompany.app.requirementApp.view.swing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

import com.mycompany.app.requirementApp.controller.RequirementController;
import com.mycompany.app.requirementApp.model.booleanLogic.BooleanVariable;
import com.mycompany.app.requirementApp.model.requirements.Requirement;
import com.mycompany.app.requirementApp.model.requirements.RequirementParser;
import com.mycompany.app.requirementApp.repository.RealRequirementRepository;
import com.mycompany.app.requirementApp.repository.RequirementWithId;

public class ModelViewControllerIT extends AssertJSwingJUnitTestCase {

	private FrameFixture window;
	private RequirementController requirementController;
	private RealRequirementRepository requirementRepository;

	@Override
	protected void onSetUp() throws Exception {
		requirementRepository = new RealRequirementRepository();
		// explicit empty the database through the repository
		for (RequirementWithId requirement : requirementRepository.findAll()) {
			requirementRepository.delete(requirement.getId());
		}
		window = new FrameFixture(robot(), GuiActionRunner.execute(() -> {
			RequirementSwingView requirementSwingView = new RequirementSwingView();
			requirementController = new RequirementController(requirementSwingView, requirementRepository,
					new RequirementParser());
			requirementSwingView.setRequirementController(requirementController);
			return requirementSwingView;
		}));
		window.show(); // shows the frame to test
	}

	@Test
	public void testAddRequirement() {
		// use the UI to add a requirement...
		window.textBox("idTextBox").enterText("1");
		window.textBox("requirementTextBox").enterText("when a then b");
		window.button(JButtonMatcher.withText("Add")).click();
		// ...verify that it has been added to the database
		Requirement req = new Requirement(new BooleanVariable("a"), "b");
		RequirementWithId reqEqual = new RequirementWithId("1", req);
		assertThat(requirementRepository.findById("1")).isEqualTo(reqEqual);
	}

	@Test
	public void testDeleteRequirement() {
		// add a requirement needed for tests
		Requirement req = mock(Requirement.class);
		requirementRepository.save(new RequirementWithId("99", req));
		// use the controller's allRequirements to make the requirements
		// appear in the GUI list
		GuiActionRunner.execute(() -> requirementController.allRequirements());
		// ...select the existing requirements
		window.list("requirementList").selectItem(0);
		window.button(JButtonMatcher.withText("Delete Selected")).click();
		// verify that the requirement has been deleted from the db
		assertThat(requirementRepository.findById("99")).isNull();
	}
}
