package com.gestionale.testGestionale.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bu", schema = "tabelle_gestionale")
public class BuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bu")
    private int idBu;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cod_resp", nullable = false)
    private int codResp;

    @OneToMany(mappedBy = "bu", cascade = CascadeType.ALL)
    private List<LavoratoreEntity> lavoratori;

    public BuEntity() {
        super();
    }

    public BuEntity(int idBu, String nome, int codResp, List<LavoratoreEntity> lavoratori) {
        this.idBu = idBu;
        this.nome = nome;
        this.codResp = codResp;
        this.lavoratori = lavoratori;
    }

    public int getIdBu() {
        return idBu;
    }

    public void setIdBu(int idBu) {
        this.idBu = idBu;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodResp() {
        return codResp;
    }

    public void setCodResp(int codResp) {
        this.codResp = codResp;
    }

    public List<LavoratoreEntity> getLavoratori() {
        return lavoratori;
    }

    public void setLavoratori(List<LavoratoreEntity> lavoratori) {
        this.lavoratori = lavoratori;
    }

    @Override
    public String toString() {
        return "BuEntity{" +
                "idBu=" + idBu +
                ", nome='" + nome + '\'' +
                ", codResp=" + codResp +
                ", lavoratori=" + lavoratori +
                '}';
    }
}
