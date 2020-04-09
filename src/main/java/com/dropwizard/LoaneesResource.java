package com.dropwizard;

import com.google.common.base.Optional;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/loanees")
public class LoaneesResource {

  private LoaneeDAO loaneeDAO;

  public LoaneesResource(LoaneeDAO loaneeDAO) {
    this.loaneeDAO = loaneeDAO;
  }

  @GET
  @Path("/name")
  @UnitOfWork
  @Produces(MediaType.APPLICATION_JSON)
  public List<Loanee> findByName(@QueryParam("name") Optional<String> name) {
    if (name.isPresent()) {
      return loaneeDAO.findByName(name.get());
    } else {
      return loaneeDAO.findAll();
    }
  }

  @GET
  @Path("/{id}")
  @UnitOfWork
  @Produces(MediaType.APPLICATION_JSON)
  public Optional<Loanee> findById(@PathParam("id") LongParam id) {
    return loaneeDAO.findById(id.get());
  }


  @POST
  @Path("/save")
  @UnitOfWork
  public long save(Loanee loanee) {
    return loaneeDAO.create(loanee);
  }
}
