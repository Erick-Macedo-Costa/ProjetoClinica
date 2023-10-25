package com.example.mapeamento.mapeamento.model.repository;

import com.example.mapeamento.mapeamento.model.entity.Consulta;
import com.example.mapeamento.mapeamento.model.entity.Paciente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConsultaRepository {
    @PersistenceContext
    private EntityManager em;

    public Consulta consulta(Long id){
        return em.find(Consulta.class, id);
    }
    public List<Consulta> consultas(){
        Query query = em.createQuery("from Consulta");
        return query.getResultList();
    }
    public void save(Consulta consulta){
        em.persist(consulta);
    }
    public void remove(Long id){
        Consulta c = em.find(Consulta.class, id);
        em.remove(c);
    }

    public void update(Consulta consulta){
        em.merge(consulta);
    }

    public List<Consulta> consultas_medico(Long id){
        Query query = em.createQuery("from Consulta as consulta where consulta.medico.id = " + id);
        return query.getResultList();
    }
    public List<Consulta> consultas_paciente(Long id){
        Query query = em.createQuery("from Consulta as consulta where consulta.paciente.id = " + id);
        return query.getResultList();
    }
}
