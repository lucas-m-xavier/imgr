/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.model;

/**
 *
 * @author Lucas
 */
public class Permission {
    private int idUser;
    
    private int idImage;
    
    private boolean view;
    
    private boolean delete;
    
    private boolean share;

    public Permission() {
    }

    public Permission(int idUser, int idImage, boolean view, boolean delete, boolean share) {
        this.idUser = idUser;
        this.idImage = idImage;
        this.view = view;
        this.delete = delete;
        this.share = share;
    }
    
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isShare() {
        return share;
    }

    public void setShare(boolean share) {
        this.share = share;
    }
}
