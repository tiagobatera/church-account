package br.com.church.account.repository;
import br.com.church.account.dto.ExpensesDto;
import br.com.church.account.model.ExpenseEntity;
import br.com.church.account.repository.mapper.ExpensesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

@Repository
public class ExpenseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ExpenseEntity> findAll() {
        String query = "SELECT * FROM EXPENSE";
        List<ExpenseEntity> entityList = jdbcTemplate.query(query, new ExpensesMapper());
        return entityList;
    }

    public ExpenseEntity findByName(String name) {
        Object[] parameters = new Object[]{name};
        ExpenseEntity expenseEntity = jdbcTemplate.queryForObject("SELECT * FROM EXPENSE WHERE name = ?", parameters, new ExpensesMapper());
        return expenseEntity;
    }

    public ExpenseEntity findById(Long id) {
        Object[] parameters = new Object[]{id};
        ExpenseEntity expenseEntity = jdbcTemplate.queryForObject("SELECT * FROM EXPENSE WHERE id = ?", parameters, new ExpensesMapper());
        return expenseEntity;

    }
    public ExpenseEntity save(ExpensesDto expensesDto){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "INSERT INTO EXPENSE(CREATION_DATE, NAME, RESPONSIBLE, AMOUNT) VALUES (?, ?, ?, ?)";
        expensesDto.getCreation_date().getTime();
        Date data = expensesDto.getCreation_date();

        java.sql.Date date = new java.sql.Date(data.getTime());
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, date);
            preparedStatement.setString(2, expensesDto.getName());
            preparedStatement.setString(3, expensesDto.getResponsible());
            preparedStatement.setDouble(4, expensesDto.getAmount());
            return preparedStatement; }, keyHolder);

        ExpenseEntity expenseEntity = findById(keyHolder.getKey().longValue());
        return expenseEntity;

       }
       public void delete(Long id){
        String query = "DELETE FROM EXPENSE WHERE ID = ?";
        Object[] parameters = new Object[] {id};
        jdbcTemplate.update(query,parameters);
       }

}
