package com.techshard.graphql.controller;

import com.sun.java_cup.internal.runtime.virtual_parse_stack;
import com.techshard.graphql.dao.entity.Brand;
import com.techshard.graphql.dao.entity.Vehicle;
import com.techshard.graphql.dao.repository.VehicleRepository;
import com.techshard.graphql.service.VehicleService;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.tools.tree.NewArrayExpression;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@RequestMapping("/vehiclekt")
@Controller
public class MyController {

    @Autowired
    VehicleService vehicleService;

    @Autowired
    VehicleRepository repository;

//    @PostMapping
//    public ResponseEntity<Object> getData(@RequestBody final String query) {
////        ExecutionResult execute = expenseService.getGraphQL().execute(query);
////        return new ResponseEntity<>(execute, HttpStatus.OK);
//        return null;
//    }



    @PostMapping
        public List<Vehicle> getData(@RequestBody final String query) {
//        ExecutionResult execute = expenseService.getGraphQL().execute(query);
//       return new ResponseEntity<>(execute, HttpStatus.OK);
        List<Vehicle> result = vehicleService.getAllVehicles(4);
        return result;
    }

    @GetMapping
    public List<Vehicle> getData() {
//        ExecutionResult execute = expenseService.getGraphQL().execute(query);
//       return new ResponseEntity<>(execute, HttpStatus.OK);
        List<Vehicle> result = vehicleService.getAllVehicles(4);
        return result;
    }

    @PostConstruct
    public void loadSchema() throws IOException {
        loadDataToDB();
        //get schema file
     }

    private void loadDataToDB() {
        Stream.of(
                new Vehicle(1, "Car", "sdfdf", Brand.HONDA, LocalDateTime.now(),"1"),
                new Vehicle(2,"Car2", "March 2020", Brand.HONDA,LocalDateTime.now(),"2"),
                new Vehicle(3,"Car3", "March 2020", Brand.HONDA,LocalDateTime.now(),"3"),
                new Vehicle(4,"Car4", "March 2020", Brand.HONDA,LocalDateTime.now(),"4")

        ).forEach(expense -> repository.save(expense));
    }

}
