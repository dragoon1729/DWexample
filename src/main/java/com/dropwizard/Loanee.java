package com.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "loanees")
@NamedQueries(
    {@NamedQuery(name = "com.dropwizard.Loanee.findAll", query = "select e from Loanee e"),
        @NamedQuery(name = "com.dropwizard.Loanee.findByName",
            query = "select e from Loanee e where e.firstName like :name or e.lastName like "
                + ":name")})
public class Loanee {

  public long getId() {
    return id;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "first_name")
  @JsonProperty(value = "firstName")
  private String firstName;

  @Column(name = "last_name")
  @JsonProperty(value = "lastName")
  private String lastName;

  @JsonProperty(value = "phone")
  private String phone;

  public Loanee() {
  }

  public Loanee(String firstName, String lastName, String phone) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
  }
}