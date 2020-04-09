package com.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DWApplication extends Application<DWConfiguration> {

  private final HibernateBundle<DWConfiguration> hibernateBundle =
      new HibernateBundle<DWConfiguration>(Loanee.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(DWConfiguration configuration) {
          return configuration.getDataSourceFactory();
        }
      };


  @Override
  public void initialize(final Bootstrap<DWConfiguration> bootstrap) {
    bootstrap.addBundle(hibernateBundle);
  }

  @Override
  public void run(final DWConfiguration configuration, final Environment environment) {
    final LoaneeDAO loaneeDAO = new LoaneeDAO(hibernateBundle.getSessionFactory());
    environment.jersey().register(new LoaneesResource(loaneeDAO));
  }

  public static void main(String[] args) throws Exception {
    new DWApplication().run(args);
  }
}