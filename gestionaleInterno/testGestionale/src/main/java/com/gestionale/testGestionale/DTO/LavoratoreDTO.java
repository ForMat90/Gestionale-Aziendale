package com.gestionale.testGestionale.DTO;

import java.util.Date;

public class LavoratoreDTO {

    private int idLav;

    private String cognome;

    private String nome;

    private String ragioneSociale;

    private String nomeBu;

    private String codFisc;

    private Date dataAssunzione;

    private Date dataFine;

    private int teamLeader;

    private int manager;

    public LavoratoreDTO() {
    }

    public LavoratoreDTO(int idLav, String cognome, String nome, String ragioneSociale, String nomeBu, String codFisc, Date dataAssunzione, Date dataFine, int teamLeader, int manager) {
        this.idLav = idLav;
        this.cognome = cognome;
        this.nome = nome;
        this.ragioneSociale = ragioneSociale;
        this.nomeBu = nomeBu;
        this.codFisc = codFisc;
        this.dataAssunzione = dataAssunzione;
        this.dataFine = dataFine;
        this.teamLeader = teamLeader;
        this.manager = manager;
    }

    public int getIdLav() {
        return idLav;
    }

    public void setIdLav(int idLav) {
        this.idLav = idLav;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getNomeBu() {
        return nomeBu;
    }

    public void setNomeBu(String nomeBu) {
        this.nomeBu = nomeBu;
    }

    public String getCodFisc() {
        return codFisc;
    }

    public void setCodFisc(String codFisc) {
        this.codFisc = codFisc;
    }

    public Date getDataAssunzione() {
        return dataAssunzione;
    }

    public void setDataAssunzione(Date dataAssunzione) {
        this.dataAssunzione = dataAssunzione;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    public int getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(int teamLeader) {
        this.teamLeader = teamLeader;
    }

    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "LavoratoreDTO{" +
                "idLav=" + idLav +
                ", cognome='" + cognome + '\'' +
                ", nome='" + nome + '\'' +
                ", ragioneSociale='" + ragioneSociale + '\'' +
                ", nomeBu='" + nomeBu + '\'' +
                ", codFisc='" + codFisc + '\'' +
                ", dataAssunzione=" + dataAssunzione +
                ", dataFine=" + dataFine +
                ", TeamLeader='" + teamLeader + '\'' +
                ", Manager='" + manager + '\'' +
                '}';
    }
}
