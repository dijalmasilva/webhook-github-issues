package br.com.dijalmasilva.octoevents.web;

import br.com.dijalmasilva.octoevents.model.Issue;
import br.com.dijalmasilva.octoevents.service.IssueService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * @author <a href="https://dijalmasilva.github.io" target="_blank">Dijalma Silva</a>
 **/
@RestController
@AllArgsConstructor
public class WebhookController {

    private IssueService issueService;

    @PostMapping
    public ResponseEntity webhook(@RequestBody String payload) {
        try {
            Issue issue = issueService.saveIssue(payload);
            return ResponseEntity.created(URI.create("/issues/" + issue.getNumber() + "/events")).body(issue);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/issues/{number}/events")
    public ResponseEntity events(@PathVariable Long number) {
        return ResponseEntity.ok(issueService.getIssuesFromNumber(number));
    }

}
