package com.gestionale.testGestionale.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="aziende", schema = "tabelle_gestionale")
public class AziendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_az")
    private int idAz;

    @Column(name = "ragione_sociale", nullable = false)
    private String ragioneSociale;

    @Column(name = "citta", nullable = false)
    private String citta;

    @Column(name = "indirizzo", nullable = false)
    private String indirizzo;

    @Column(name = "p_iva", nullable = false)
    private String pIva;

    @OneToMany(mappedBy = "aziende", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LavoratoreEntity> lavoratori;


    public AziendaEntity() {
    }

    public AziendaEntity(int idAz, String ragioneSociale, String citta, String indirizzo, String pIva, List<LavoratoreEntity> lavoratori) {
        this.idAz = idAz;
        this.ragioneSociale = ragioneSociale;
        this.citta = citta;
        this.indirizzo = indirizzo;
        this.pIva = pIva;
        this.lavoratori = lavoratori;
    }

    public int getIdAz() {
        return idAz;
    }

    public void setIdAz(int idAz) {
        this.idAz = idAz;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getpIva() {
        return pIva;
    }

    public void setpIva(String pIva) {
        this.pIva = pIva;
    }

    public List<LavoratoreEntity> getLavoratori() {
        return lavoratori;
    }

    public void setLavoratori(List<LavoratoreEntity> lavoratori) {
        this.lavoratori = lavoratori;
    }

    @Override
    public String toString() {
        return "AziendaEntity{" +
                "idAz=" + idAz +
                ", ragioneSociale='" + ragioneSociale + '\'' +
                ", citta='" + citta + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", pIva='" + pIva + '\'' +
                ", lavoratori=" + lavoratori +
                '}';
    }
}