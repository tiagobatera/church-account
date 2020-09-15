package br.com.church.account.controllers;

import br.com.church.account.dto.TitheDto;
import br.com.church.account.model.TitheEntity;
import br.com.church.account.repository.TitheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/tithe")
public class TitheController {

    @Autowired
    private TitheRepository titheRepository;
    private String atributoGlobal = "";

    @GetMapping("/all")
    public ResponseEntity<List<TitheEntity>> listAll(){
        List<TitheEntity> all = titheRepository.findAll();
        String atributoLocal = "";
        return ResponseEntity.ok(all);
    }
    @GetMapping("/findByName/{name}")
    public ResponseEntity<TitheDto> findTitheByName(@PathVariable("name") String name){
        return ResponseEntity.ok(new TitheDto(titheRepository.findByName(name)));
    }
    @PostMapping
    public ResponseEntity<TitheDto> create(@RequestBody TitheDto titheDto, UriComponentsBuilder uriBuilder){

        TitheEntity titheEntity = titheRepository.save(titheDto);
        URI uri = uriBuilder.path("/v1/tithe/{id}").buildAndExpand(titheEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(new TitheDto(titheEntity));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") long id){
        titheRepository.delete(id);
        return ResponseEntity.ok().build();
    }

}
