package com.crud.socio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/socio")
@CrossOrigin(origins = "*")
public class SocioController {

    @Autowired
    SocioService socioServicio;

    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody Socio socio) {
        socioServicio.saveSocio(socio);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Socio>> list() {
        List<Socio> list = socioServicio.listSocio();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    //mostrar por id
    @GetMapping("/detail/{id}")
    public ResponseEntity<Socio> getById(@PathVariable("id") Integer id) {
        if (!socioServicio.isExistSocio(id)) // si no existe el id
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Socio socio = socioServicio.getOne(id).get();  //mostrar datos de un socio
        return new ResponseEntity(socio, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
          if (!socioServicio.isExistSocio(id)) // si no existe el id
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        socioServicio.deleteSocio(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Socio socio) {
            if (!socioServicio.isExistSocio(id)) // si no existe el id
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Socio pers1 = socioServicio.getOne(id).get();

        pers1.setNombre(socio.getNombre());
        pers1.setApellido(socio.getApellido());
        pers1.setDomicilio(socio.getDomicilio());
        pers1.setEdad(socio.getEdad());
        pers1.setLocalidad(socio.getLocalidad());
        pers1.setFechaNacimiento(socio.getFechaNacimiento());
        socioServicio.saveSocio(pers1);
        return new ResponseEntity(HttpStatus.OK);
    }
}
