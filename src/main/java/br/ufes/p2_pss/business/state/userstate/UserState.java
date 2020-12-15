/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.business.state.userstate;

import br.ufes.p2_pss.model.User;

/**
 *
 * @author Lucas
 */
public abstract class UserState {
    private User usuario;

    public UserState(User usuario) {
        this.usuario = usuario;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
}
