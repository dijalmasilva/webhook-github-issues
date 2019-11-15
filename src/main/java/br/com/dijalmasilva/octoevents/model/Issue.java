package br.com.dijalmasilva.octoevents.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author <a href="https://dijalmasilva.github.io" target="_blank">Dijalma Silva</a>
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issue implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "ISSUE_NUMBER")
    private Long number;

    @Column(name = "PAYLOAD", columnDefinition = "text")
    private String payload;
}
