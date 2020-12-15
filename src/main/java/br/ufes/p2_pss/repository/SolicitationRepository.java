/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.repository;

import br.ufes.p2_pss.dao.SolicitationDAO;
import br.ufes.p2_pss.model.Solicitation;
import br.ufes.p2_pss.model.User;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class SolicitationRepository {
    private final SolicitationDAO solicitationDAO;

    public SolicitationRepository() throws Exception {
        this.solicitationDAO = new SolicitationDAO();
    }
    
    public ArrayList<Solicitation> getAllByAdmin(User admin) throws Exception {
        return solicitationDAO.getAllByAdmin(admin);
    }
    
    public void save(Solicitation solicitation) throws Exception {
        solicitationDAO.save(solicitation);
    }
    
    public void delete(int id) throws Exception {
        solicitationDAO.delete(id);
    }
}
