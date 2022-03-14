package com.mycompany.app.requirementApp.controller;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mycompany.app.requirementApp.model.requirements.NoGoodRequirementFormatException;
import com.mycompany.app.requirementApp.model.requirements.Requirement;
import com.mycompany.app.requirementApp.model.requirements.RequirementParser;
import com.mycompany.app.requirementApp.repository.RequirementRepository;
import com.mycompany.app.requirementApp.repository.RequirementWithId;
import com.mycompany.app.requirementApp.view.RequirementView;

public class RequirementControllerTest {

	private RequirementView reqView;
	private RequirementRepository reqRepo;
	private RequirementParser reqPar;
	private RequirementController reqController;

	@Before
	public void setup() {
		reqView = mock(RequirementView.class);
		reqRepo = mock(RequirementRepository.class);
		reqPar = mock(RequirementParser.class);
		reqController = new RequirementController(reqView, reqRepo, reqPar);
	}

	@Test
	public void testControllerAllRequirements() {
		verifyNoInteractions(reqRepo);
		verifyNoInteractions(reqView);

		List<RequirementWithId> list = new ArrayList<RequirementWithId>();
		when(reqRepo.findAll()).thenReturn(list);

		reqController.allRequirements();
		verify(reqView).showAllRequirements(list);
		verify(reqRepo).findAll();
		verifyNoMoreInteractions(reqView);
		verifyNoMoreInteractions(reqRepo);
	}

	@Test
	public void testControllerNewRequirementGood() {
		verifyNoInteractions(reqRepo);
		verifyNoInteractions(reqView);

		String requirement = "requirementText";
		String id = "id";

		RequirementWithId reqReturned = null;
		when(reqRepo.findById(id)).thenReturn(reqReturned);

		Requirement mockReq = mock(Requirement.class);
		try {
			when(reqPar.createRequirement(requirement)).thenReturn(mockReq);
		} catch (NoGoodRequirementFormatException e) {
			fail();
		}
		reqController.newRequirement(id, requirement);

		verify(reqRepo).findById(id);
		RequirementWithId req = new RequirementWithId(id, mockReq);
		try {
			verify(reqPar).createRequirement(requirement);
		} catch (NoGoodRequirementFormatException e) {
			fail();
		}
		verify(reqRepo).save(req);
		verify(reqView).requirementAdded(req);

		verifyNoInteractions(mockReq);
		verifyNoMoreInteractions(reqView);
		verifyNoMoreInteractions(reqRepo);
	}

	@Test
	public void testControllerNewRequirementAlreadyExisting() {
		verifyNoInteractions(reqRepo);
		verifyNoInteractions(reqView);

		Requirement requirement = mock(Requirement.class);
		Requirement existingRequirement = mock(Requirement.class);
		String id = "id";
		String requirementText = "requirementText";
		RequirementWithId req = new RequirementWithId(id, requirement);

		RequirementWithId existingReq = new RequirementWithId(id, existingRequirement);

		when(reqRepo.findById(id)).thenReturn(existingReq);

		reqController.newRequirement(id, requirementText);

		verify(reqRepo).findById(id);
		verify(reqView).showError("Already existing requirement with id " + req.getId() + ": " + existingReq);

		verifyNoInteractions(requirement);

		verifyNoMoreInteractions(reqView);
		verifyNoMoreInteractions(reqRepo);
	}

	@Test
	public void testControllerNewRequirementWrongFormat() throws NoGoodRequirementFormatException {
		verifyNoInteractions(reqRepo);
		verifyNoInteractions(reqView);

		String id = "id";
		String requirementText = "requirementText";
		when(reqPar.createRequirement(requirementText))
				.thenThrow(new NoGoodRequirementFormatException("error with the format"));

		when(reqRepo.findById(id)).thenReturn(null);

		reqController.newRequirement(id, requirementText);

		verify(reqRepo).findById(id);
		verify(reqView).showError("error with the format");

		verifyNoMoreInteractions(reqView);
		verifyNoMoreInteractions(reqRepo);
	}

	@Test
	public void testControllerImportRequirementGood() throws NoGoodRequirementFormatException {
		verifyNoInteractions(reqRepo);
		verifyNoInteractions(reqView);

		String id1 = "id_1";
		String id2 = "id_2";
		RequirementWithId reqReturned = null;

		when(reqRepo.findById("id_1")).thenReturn(reqReturned);
		when(reqRepo.findById("id_2")).thenReturn(reqReturned);

		Requirement mockReq1 = mock(Requirement.class);
		Requirement mockReq2 = mock(Requirement.class);
		when(reqPar.createRequirement("requirement text 1")).thenReturn(mockReq1);
		when(reqPar.createRequirement("requirement text 2")).thenReturn(mockReq2);
		RequirementWithId req1 = new RequirementWithId(id1, mockReq1);
		RequirementWithId req2 = new RequirementWithId(id2, mockReq2);
		String newLineChar = System.getProperty("line.separator");
		String reqText = "id_1 requirement text 1" + newLineChar + "id_2 requirement text 2";
		reqController.importRequirements(reqText);

		verify(reqRepo).findById(id1);
		verify(reqRepo).findById(id2);
		verify(reqPar).createRequirement("requirement text 1");
		verify(reqPar).createRequirement("requirement text 2");
		List<RequirementWithId> requirements = new ArrayList<RequirementWithId>();
		requirements.add(req1);
		requirements.add(req2);
		verify(reqRepo).save(req1);
		verify(reqRepo).save(req2);
		verify(reqView).importRequirements(requirements);

		verifyNoInteractions(mockReq1);
		verifyNoInteractions(mockReq2);
		verifyNoMoreInteractions(reqView);
		verifyNoMoreInteractions(reqRepo);
	}

	@Test
	public void testControllerImportRequirementEmpty() throws NoGoodRequirementFormatException {
		verifyNoInteractions(reqRepo);
		verifyNoInteractions(reqView);
		String reqText = "id_1 requirement text 1" + System.getProperty("line.separator") + ""
				+ System.getProperty("line.separator") + "id_2 requirement text 2";
		reqController.importRequirements(reqText);

		verifyNoInteractions(reqPar);
		verifyNoInteractions(reqRepo);
		verify(reqView).showImportError("there is one or more empty line between the requirements to import");
		verifyNoMoreInteractions(reqView);
		verifyNoMoreInteractions(reqView);
		verifyNoMoreInteractions(reqRepo);
	}

	@Test
	public void testControllerImportRequirementWithNoSpaces() throws NoGoodRequirementFormatException {
		verifyNoInteractions(reqRepo);
		verifyNoInteractions(reqView);
		String reqText = "id_1 requirement text 1" + System.getProperty("line.separator")
				+ "id_1_requirement_with_no_spaces" + System.getProperty("line.separator") + "id_2 requirement text 2";
		reqController.importRequirements(reqText);

		verifyNoInteractions(reqPar);
		verifyNoInteractions(reqRepo);
		verify(reqView).showImportError(
				"there is at least a requirement with a wrong format: id_1_requirement_with_no_spaces");
		verifyNoMoreInteractions(reqView);
		verifyNoMoreInteractions(reqView);
		verifyNoMoreInteractions(reqRepo);
	}

	@Test
	public void testControllerImportRequirementNoGoodFormat() throws NoGoodRequirementFormatException {
		verifyNoInteractions(reqRepo);
		verifyNoInteractions(reqView);
		
		RequirementWithId reqReturned = null;

		when(reqRepo.findById("id_1")).thenReturn(reqReturned);
		when(reqRepo.findById("id_2")).thenReturn(reqReturned);

		Requirement mockReq1 = mock(Requirement.class);
		when(reqPar.createRequirement("requirement text 1")).thenReturn(mockReq1);
		when(reqPar.createRequirement("requirement text 2")).thenThrow(new NoGoodRequirementFormatException("error message"));
		String newLineChar = System.getProperty("line.separator");
		String reqText = "id_1 requirement text 1" + newLineChar + "id_2 requirement text 2";
		reqController.importRequirements(reqText);

		verify(reqPar).createRequirement("requirement text 1");
		verify(reqPar).createRequirement("requirement text 2");
		verify(reqView).showImportError("there is at least one format error. error message: requirement text 2");;
	
		verifyNoMoreInteractions(reqView);
		verifyNoInteractions(reqRepo);
	}
	
	@Test
	public void testControllerImportRequirementAlreadyImported() throws NoGoodRequirementFormatException {
		verifyNoInteractions(reqRepo);
		verifyNoInteractions(reqView);

		String id1 = "id_1";
		String id2 = "id_2";
		RequirementWithId reqReturned = null;
		
		RequirementWithId req2InRepo = new RequirementWithId(id2, mock(Requirement.class));

		when(reqRepo.findById("id_1")).thenReturn(reqReturned);
		when(reqRepo.findById("id_2")).thenReturn(req2InRepo);

		Requirement mockReq1 = mock(Requirement.class);
		Requirement mockReq2 = mock(Requirement.class);
		when(reqPar.createRequirement("requirement text 1")).thenReturn(mockReq1);
		when(reqPar.createRequirement("requirement text 2")).thenReturn(mockReq2);
		String newLineChar = System.getProperty("line.separator");
		String reqText = "id_1 requirement text 1" + newLineChar + "id_2 requirement text 2";
		reqController.importRequirements(reqText);

		verify(reqRepo).findById(id1);
		verify(reqRepo).findById(id2);
		verify(reqPar).createRequirement("requirement text 1");
		verify(reqPar).createRequirement("requirement text 2");
		verify(reqView).showImportError("at least one requirement to be imported has the same id of a present requirement: "+"id_2");
		
		verifyNoInteractions(mockReq1);
		verifyNoInteractions(mockReq2);
		verifyNoMoreInteractions(reqView);
		verifyNoMoreInteractions(reqRepo);
	}
	
	@Test
	public void testControllerImportRequirementWithSameId() throws NoGoodRequirementFormatException {
		verifyNoInteractions(reqRepo);
		verifyNoInteractions(reqView);

		String id1 = "id_1";
		String id2 = "id_2";
		RequirementWithId reqReturned = null;
		
		RequirementWithId req2InRepo = new RequirementWithId(id2, mock(Requirement.class));

		when(reqRepo.findById("id_1")).thenReturn(reqReturned);
		when(reqRepo.findById("id_2")).thenReturn(req2InRepo);

		Requirement mockReq1 = mock(Requirement.class);
		Requirement mockReq2 = mock(Requirement.class);
		when(reqPar.createRequirement("requirement text 1")).thenReturn(mockReq1);
		when(reqPar.createRequirement("requirement text 2")).thenReturn(mockReq2);
		String newLineChar = System.getProperty("line.separator");
		String reqText = "id_1 requirement text 1" + newLineChar + "id_1 requirement text 2";
		reqController.importRequirements(reqText);

		verify(reqRepo, times(2)).findById(id1);
		verify(reqPar).createRequirement("requirement text 1");
		verify(reqPar).createRequirement("requirement text 2");
		verify(reqView).showImportError("at least two requirements to be imported has the same id: "+"id_1");
		
		verifyNoInteractions(mockReq1);
		verifyNoInteractions(mockReq2);
		verifyNoMoreInteractions(reqView);
		verifyNoMoreInteractions(reqRepo);
	}

}
