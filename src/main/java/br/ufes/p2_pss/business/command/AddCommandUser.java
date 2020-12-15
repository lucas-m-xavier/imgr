/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.business.command;

import br.ufes.p2_pss.business.state.cruduserstate.CrudUserState;

/**
 *
 * @author Lucas
 */
public class AddCommandUser implements ICommandUserPresenter{
    
    private CrudUserState state;

    public AddCommandUser(CrudUserState state) {
        this.state = state;
    }
    
    @Override
    public void execute() {
        this.state.saveUser();
    }
}
