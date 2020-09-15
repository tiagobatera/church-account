package br.com.church.account.controllers;
import br.com.church.account.dto.ExpensesDto;
import br.com.church.account.dto.LoginDto;
import br.com.church.account.model.ExpenseEntity;
import br.com.church.account.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/expenses")
public class ExpensesController {

    @Autowired
    private ExpenseRepository expenseRepository;
    private String atributoGlobal = "";


    @RequestMapping("/all")
    public ResponseEntity<List<ExpenseEntity>> listAll() {
        List<ExpenseEntity> all = expenseRepository.findAll();
        String atributoLocal = "";
        return ResponseEntity.ok(all);
    }
    @RequestMapping("/{name}")
    public ResponseEntity<ExpensesDto> findExpensesByName(@PathVariable("name") String name){
        return ResponseEntity.ok(new ExpensesDto(expenseRepository.findByName(name)));
    }
    @PostMapping
    public ResponseEntity<ExpensesDto> create(@RequestBody ExpensesDto expensesDto, UriComponentsBuilder uriBuilder){
        ExpenseEntity expenseEntity = expenseRepository.save(expensesDto);
        URI uri = uriBuilder.path("/v1/{id}").buildAndExpand(expenseEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(new ExpensesDto(expenseEntity));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){

        expenseRepository.delete(id);
        return ResponseEntity.ok().build();
    }


}
