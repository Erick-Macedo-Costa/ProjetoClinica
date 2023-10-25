package com.example.mapeamento.mapeamento.model.repository;

import com.example.mapeamento.mapeamento.model.entity.Medico;
import com.example.mapeamento.mapeamento.model.entity.Paciente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicoRepository {
    @PersistenceContext
    private EntityManager em;

    public Medico medico(Long id){
        return em.find(Medico.class, id);
    }
    public List<Medico> medicos(){
        Query query = em.createQuery("from Medico");
        return query.getResultList();
    }
    public void save(Medico medico){
        em.persist(medico);
    }
    public void remove(Long id){
        Medico m = em.find(Medico.class, id);
        em.remove(m);
    }

    public void update(Medico medico){
        em.merge(medico);
    }
}
