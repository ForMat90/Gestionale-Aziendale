package com.gestionale.testGestionale.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "commesse", schema = "tabelle_gestionale")
public class CommessaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comm")
    private int idComm;

    @Column(name = "nome_prog", nullable = false)
    private String nomeProg;

    @Column(name = "committente", nullable = false)
    private String committente;

    @Column(name = "data_inizio_commessa", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataInizioCommessa;

    @ManyToMany(mappedBy = "commesse", fetch = FetchType.EAGER)
    @JsonIgnore
    List<LavoratoreEntity> lavoratori = new ArrayList<>();

    public CommessaEntity() {
        super();
    }

    public CommessaEntity(int idComm, String nomeProg, String committente, Date dataInizioCommessa, List<LavoratoreEntity> lavoratori) {
        this.idComm = idComm;
        this.nomeProg = nomeProg;
        this.committente = committente;
        this.dataInizioCommessa = dataInizioCommessa;
        this.lavoratori = lavoratori;
    }

    public int getIdComm() {
        return idComm;
    }

    public void setIdComm(int idComm) {
        this.idComm = idComm;
    }

    public String getNomeProg() {
        return nomeProg;
    }

    public void setNomeProg(String nomeProg) {
        this.nomeProg = nomeProg;
    }

    public String getCommittente() {
        return committente;
    }

    public void setCommittente(String committente) {
        this.committente = committente;
    }

    public Date getDataInizioCommessa() {
        return dataInizioCommessa;
    }

    public void setDataInizioCommessa(Date dataInizioCommessa) {
        this.dataInizioCommessa = dataInizioCommessa;
    }

    public List<LavoratoreEntity> getLavoratori() {
        return lavoratori;
    }

    public void setLavoratori(List<LavoratoreEntity> lavoratori) {
        this.lavoratori = lavoratori;
    }

    @Override
    public String toString() {
        return "CommessaEntity{" +
                "idComm=" + idComm +
                ", nomeProg='" + nomeProg + '\'' +
                ", committente='" + committente + '\'' +
                ", dataInizioCommessa=" + dataInizioCommessa +
                ", lavoratori=" + lavoratori +
                '}';
    }

}