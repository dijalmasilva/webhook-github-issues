package br.com.dijalmasilva.octoevents.service;

import br.com.dijalmasilva.octoevents.model.Issue;
import br.com.dijalmasilva.octoevents.repository.IssueRepository;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author <a href="https://dijalmasilva.github.io" target="_blank">Dijalma Silva</a>
 **/
@Service
@AllArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;

    public Issue saveIssue(String issuePayload) throws RuntimeException {
        JSONObject jsonObject = new JSONObject(issuePayload);
        JSONObject issueJson = jsonObject.getJSONObject("issue");
        long number = issueJson.getLong("number");
        Issue issue = new Issue();
        issue.setNumber(number);
        issue.setPayload(issuePayload);

        Issue saved = issueRepository.save(issue);
        if (Objects.isNull(saved)){
            throw new RuntimeException("Error to persist issue.");
        }

        return saved;
    }

    public List<Map<String, Object>> getIssuesFromNumber(Long number) {
         return issueRepository.findByNumber(number).stream().map(issue -> {
             String payload = issue.getPayload();
             JSONObject jsonObject = new JSONObject(payload);
             return jsonObject.toMap();
         }).collect(Collectors.toList());
    }
}
