package br.com.dijalmasilva.octoevents.repository;

import br.com.dijalmasilva.octoevents.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author <a href="https://dijalmasilva.github.io" target="_blank">Dijalma Silva</a>
 **/
public interface IssueRepository  extends JpaRepository<Issue, Long> {

    List<Issue> findByNumber(Long number);
}
