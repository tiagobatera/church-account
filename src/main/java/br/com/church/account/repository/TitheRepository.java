package br.com.church.account.repository;

import br.com.church.account.dto.TitheDto;
import br.com.church.account.model.TitheEntity;
import br.com.church.account.repository.mapper.TitheMapper;
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
public class TitheRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<TitheEntity> findAll() {
        String query = "SELECT * FROM TITHE";
        List<TitheEntity> entitiesList = jdbcTemplate.query(query, new TitheMapper());
        return entitiesList;
    }

    public TitheEntity findByName(String name) {
        Object[] parameters = new Object[]{name};
        TitheEntity titheEntity = jdbcTemplate.queryForObject("SELECT * FROM TITHE WHERE NAME = ?", parameters, new TitheMapper());
        return titheEntity;
    }
    public TitheEntity findById(long id) {
        Object[] parameters = new Object[]{id};
        TitheEntity titheEntity = jdbcTemplate.queryForObject("SELECT * FROM TITHE WHERE ID = ?", parameters, new TitheMapper());
        return titheEntity;
    }
    public TitheEntity save(TitheDto titheDto){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "INSERT INTO TITHE (CREATION_DATE, NAME, VALUE, TYPE) VALUES (?, ?, ?, ?)";

        //conversion date java.util.date > java.sql.date
        java.sql.Date date;

        if(titheDto.getCreation_date() != null){
            Date data = titheDto.getCreation_date();
            date = new java.sql.Date(data.getTime());
        }else{
            date = new java.sql.Date(new Date().getTime());
        }

        jdbcTemplate.update(connection -> {
        PreparedStatement preparedStatement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setDate(1, date);
        preparedStatement.setString(2, titheDto.getName());
        preparedStatement.setDouble(3, titheDto.getValue());
        preparedStatement.setString(4, titheDto.getType());
        return preparedStatement;}, keyHolder);

        TitheEntity titheEntity = findById((keyHolder.getKey().longValue()));
        return titheEntity;
    }
    public void delete(long id){
        String query = "DELETE FROM TITHE WHERE ID = ?";
        Object[] parameters = new Object[]{id};
        jdbcTemplate.update(query, parameters);
    }

}
