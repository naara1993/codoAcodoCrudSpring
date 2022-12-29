
package com.crud.socio;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional   
public class SocioService  {
    
    @Autowired
    private ISocioRepository socioRepo;
    
    
    public List<Socio> listSocio(){
        return socioRepo.findAll();
    }
    
    public Optional<Socio> getOne(Integer id){
        return socioRepo.findById(id);
    }
    public boolean isExistSocio(Integer id){
        return socioRepo.existsById(id);
    }
    public void saveSocio(Socio socios){
          socioRepo.save(socios);
    }
    
    public void deleteSocio(Integer id){
        socioRepo.deleteById(id);
    }
    
    
}
