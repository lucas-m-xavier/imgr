/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.service;

import br.ufes.p2_pss.dao.SolicitationDAO;
import br.ufes.p2_pss.model.Solicitation;
import br.ufes.p2_pss.model.User;
import br.ufes.p2_pss.repository.SolicitationRepository;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class SolicitationService {
    private final SolicitationRepository solicitationRepository;

    public SolicitationService() throws Exception {
        this.solicitationRepository = new SolicitationRepository();
    }
    
    public ArrayList<Solicitation> getAllByAdmin(User admin) throws Exception {
        return solicitationRepository.getAllByAdmin(admin);
    }
    
    public void save(Solicitation solicitation) throws Exception {
        solicitationRepository.save(solicitation);
    }
    
    public void delete(int id) throws Exception {
        solicitationRepository.delete(id);
    }
}
