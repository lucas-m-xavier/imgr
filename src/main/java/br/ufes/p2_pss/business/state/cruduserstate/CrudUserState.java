/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.business.state.cruduserstate;

import br.ufes.p2_pss.presenter.user.AddUpdateUserPresenter;
import br.ufes.p2_pss.presenter.user.CRUDUserPresenter;
import br.ufes.p2_pss.service.UserService;

/**
 *
 * @author Lucas
 */
public abstract class CrudUserState {
    
    protected AddUpdateUserPresenter presenter;

    public CrudUserState(AddUpdateUserPresenter presenter) {
        this.presenter = presenter;
    }
    
    public void saveUser() {
        throw new UnsupportedOperationException("Not supported yet!");
    }
    
    public void fechar() {
        this.presenter.getView().dispose();
    }
}
