package br.com.church.account.dto;

import br.com.church.account.model.ExpenseEntity;
import br.com.church.account.model.TitheEntity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TitheDto {
    private long id;
    private Date creation_date;
    private String name;
    private double value;
    private String type;

    public TitheDto() {

    }

    public TitheDto(TitheEntity titheEntity) {
        this.id = titheEntity.getId();
        this.creation_date = titheEntity.getCreation_date();
        this.name = titheEntity.getName();
        this.value = titheEntity.getValue();
        this.type = titheEntity.getType();
    }

    public TitheDto(TitheDto titheDto) {
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static List<TitheDto> converterToList(List<TitheDto> titheDtoList) {
        return titheDtoList.stream().map(TitheDto::new).collect(Collectors.toList());

    }
}
