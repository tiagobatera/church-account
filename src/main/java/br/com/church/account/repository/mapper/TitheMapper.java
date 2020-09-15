package br.com.church.account.repository.mapper;

import br.com.church.account.model.LoginEntity;
import br.com.church.account.model.TitheEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TitheMapper implements RowMapper<TitheEntity> {

    @Override
    public TitheEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        TitheEntity titheEntity = new TitheEntity();
        titheEntity.setId(resultSet.getLong("ID"));
        titheEntity.setCreation_date(resultSet.getDate("CREATION_DATE"));
        titheEntity.setName(resultSet.getString("NAME"));
        titheEntity.setValue(resultSet.getDouble("VALUE"));
        titheEntity.setType(resultSet.getString("TYPE"));

        return titheEntity;
    }


}
