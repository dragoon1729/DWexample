package com.dropwizard;


import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class LoaneeDAO extends AbstractDAO<Loanee> {

  public LoaneeDAO(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public List<Loanee> findAll() {
    return list(namedQuery("com.dropwizard.Loanee.findAll"));
  }

  public List<Loanee> findByName(String name) {
    StringBuilder builder = new StringBuilder("%").append(name).append("%");
    return list(
        namedQuery("com.dropwizard.Loanee.findByName").setParameter("name", builder.toString()));
  }

  public Optional<Loanee> findById(long id) {
    return Optional.fromNullable(get(id));
  }

  public long create(Loanee loanee) {
    return persist(loanee).getId();
  }

}