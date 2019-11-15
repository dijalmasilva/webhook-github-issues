package br.com.dijalmasilva.octoevents.service;

import br.com.dijalmasilva.octoevents.model.Issue;
import br.com.dijalmasilva.octoevents.repository.IssueRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class IssueServiceTest {

    @InjectMocks
    private IssueService issueService;

    @Mock
    private IssueRepository issueRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Issue issueCompare;
    private Issue issue3;

    @Before
    public void setUp() {
        issueCompare = new Issue();
        issueCompare.setNumber(1L);
        issueCompare.setId(1L);
        issueCompare.setPayload("{ \"issue\": { \"number\": 1 }, \"action\": \"created\" }");

        Issue issueCompare2 = new Issue();
        issueCompare2.setNumber(1L);
        issueCompare2.setId(2L);
        issueCompare2.setPayload("{ \"issue\": { \"number\": 1 }, \"action\": \"edit\" }");

        issue3 = new Issue();
        issue3.setNumber(2L);
        issue3.setPayload("{ \"issue\": { \"number\": 2 }, \"action\": \"created\" }");

        List<Issue> issuesByNumber = Arrays.asList(issueCompare, issueCompare2);
        Mockito.when(issueRepository.save(Mockito.any(Issue.class))).thenReturn(issueCompare);
        Mockito.when(issueRepository.save(issue3)).thenReturn(issue3);
        Mockito.when(issueRepository.findByNumber(Mockito.anyLong())).thenReturn(Collections.emptyList());
        Mockito.when(issueRepository.findByNumber(1L)).thenReturn(issuesByNumber);
    }

    @Test
    public void saveIssue() {
        String payload = "{ \"issue\": { \"number\": 1 }, \"action\": \"created\" }";
        Issue issue = issueService.saveIssue(payload);
        Assert.assertEquals(issueCompare, issue);
    }

    @Test
    public void saveIssueWithOtherNumber() {
        String payload = "{ \"issue\": { \"number\": 2 }, \"action\": \"created\" }";
        Issue issue = issueService.saveIssue(payload);
        Assert.assertEquals(issue, issue3);
    }

    @Test
    public void saveIssueNumberWrongReturn() {
        String payload = "{ \"issue\": { \"number\": 3 }, \"action\": \"created\" }";
        Issue issue = issueService.saveIssue(payload);
        Assert.assertNotEquals(issue, issue3);
    }

    @Test
    public void exceptionToSave() {
        Mockito.when(issueRepository.save(Mockito.any(Issue.class))).thenReturn(null);

        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Error to persist issue.");

        String payload = "{ \"issue\": { \"number\": 3 }, \"action\": \"created\" }";
        issueService.saveIssue(payload);
    }

    @Test
    public void getIssuesFromNumber() {
        List<Map<String, Object>> issuesFromNumber = issueService.getIssuesFromNumber(1L);
        Assert.assertEquals(2, issuesFromNumber.size());
    }

    @Test
    public void getIssuesFromNumberNotFound() {
        List<Map<String, Object>> issuesFromNumber = issueService.getIssuesFromNumber(2L);
        Assert.assertEquals(0, issuesFromNumber.size());
    }
}