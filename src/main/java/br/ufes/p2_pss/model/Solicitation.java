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
public class Solicitation {
    
    private int id;
    
    private int idAdmin;
    
    private int idUser;
    
    private int idImage;
    
    private boolean view;
    
    private boolean share;
    
    private boolean delete;

    public Solicitation() {
    }

    public Solicitation(int id, int idAdmin, int idUser, int idImage, boolean view, boolean share, boolean delete) {
        this.id = id;
        this.idAdmin = idAdmin;
        this.idUser = idUser;
        this.idImage = idImage;
        this.view = view;
        this.share = share;
        this.delete = delete;
    }

    public Solicitation(int idAdmin, int idUser, int idImage, boolean view, boolean share, boolean delete) {
        this.idAdmin = idAdmin;
        this.idUser = idUser;
        this.idImage = idImage;
        this.view = view;
        this.share = share;
        this.delete = delete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
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

    public boolean isShare() {
        return share;
    }

    public void setShare(boolean share) {
        this.share = share;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
