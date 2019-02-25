package com.demo.grql.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.core.io.Resource;
import com.demo.grql.repo.EmployeeRepo;

import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.GraphQL;
import graphql.schema.idl.TypeDefinitionRegistry;

@Component
public class EmployeeService{
@Autowired
EmployeeRepo repo;

@Value("classpath:employee.graphqls")
Resource resource;

private GraphQL graphQL;
@Autowired
private AllEmployeeDataFetcher allEmployeeDataFetcher;

// load schema at application start up
@PostConstruct
private void loadSchema() throws IOException {

    
    // get the schema
    File schemaFile = resource.getFile();
    // parse schema
    TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
    RuntimeWiring wiring = buildRuntimeWiring();
    GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
    graphQL = GraphQL.newGraphQL(schema).build();
}


private RuntimeWiring buildRuntimeWiring() {
	  return RuntimeWiring.newRuntimeWiring()
              .type("Query", typeWiring -> typeWiring
                      .dataFetcher("allEmployee", allEmployeeDataFetcher))
              .build();
}

public GraphQL getGraphQL() {
    return graphQL;
}
}
