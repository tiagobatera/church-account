package br.com.church.account.dto;

import br.com.church.account.model.ExpenseEntity;
import org.springframework.expression.spel.SpelNode;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ExpensesDto {
    private long id;
    private Date creation_date;
    private String name;
    private String responsible;
    private double amount;

    public ExpensesDto(){

    }
    public ExpensesDto(ExpenseEntity expenseEntity) {
        this.id = expenseEntity.getId();
        this.creation_date = expenseEntity.getCreationDate();
        this.name = expenseEntity.getName();
        this.responsible = expenseEntity.getResponsible();
        this.amount = expenseEntity.getAmount();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public static List<ExpensesDto> converterToList(List<ExpenseEntity> expenseEntityList){
        return expenseEntityList.stream().map(ExpensesDto::new).collect(Collectors.toList());
    }
}
