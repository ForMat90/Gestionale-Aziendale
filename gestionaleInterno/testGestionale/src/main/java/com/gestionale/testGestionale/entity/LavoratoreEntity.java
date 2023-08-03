package com.gestionale.testGestionale.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name ="lavoratori", schema = "tabelle_gestionale")
public class LavoratoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lav")
    private int idLav;

    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "id_az", nullable = false)
    private int aziende;

    @Column(name = "id_bu", nullable = false)
    private int bu;

    @Column(name = "cod_fisc", nullable = false, unique = true)
    private String codFisc;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "data_assunzione", nullable = false)
    private Date dataAssunzione;

    @Column(name = "data_fine")
    private Date dataFine;

    @Column(name = "cod_tl")
    private int codTl;

    @Column(name = "cod_manag")
    private int codManag;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            schema = "tabelle_gestionale", name = "lavoratori_commesse",
            joinColumns = @JoinColumn(name = "id_lav"),
            inverseJoinColumns = @JoinColumn(name = "id_comm")
    )
    private List<CommessaEntity> commesse = new ArrayList<>();

    public LavoratoreEntity() {
        super();
    }


    public LavoratoreEntity(int idLav, String cognome, String nome, int aziende, int bu, String codFisc, Date dataAssunzione, Date dataFine, int codTl, int codManag, List<CommessaEntity> commesse) {
        this.idLav = idLav;
        this.cognome = cognome;
        this.nome = nome;
        this.aziende = aziende;
        this.bu = bu;
        this.codFisc = codFisc;
        this.dataAssunzione = dataAssunzione;
        this.dataFine = dataFine;
        this.codTl = codTl;
        this.codManag = codManag;
        this.commesse = commesse;
    }

    public Date getDataAssunzione() {
        return dataAssunzione;
    }

    public String getDataAssunzioneFormatted() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(this.dataAssunzione);
    }

    public void setDataAssunzione(String strDate) throws ParseException {
        Date oldDate = this.dataAssunzione;
        if (strDate != null) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date newDate = formatter.parse(strDate);
            this.dataAssunzione = newDate;
        }
        else {
            this.dataAssunzione = oldDate;
        }
    }

    public Date getDataFine() {
        return dataFine;
    }

    public String getDataFineFormatted() {
        if (this.dataFine != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.format(this.dataFine);
        }
        return "";
    }

    public void setDataFine(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedCurrentDate = sdf.parse(strDate);
        this.dataFine = convertedCurrentDate;
        if (strDate == null || strDate.isEmpty()) {
            this.dataFine = null;
        }
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

    public int getAziende() {
        return aziende;
    }

    public void setAziende(int aziende) {
        this.aziende = aziende;
    }

    public int getBu() {
        return bu;
    }

    public void setBu(int bu) {
        this.bu = bu;
    }

    public String getCodFisc() {
        return codFisc;
    }

    public void setCodFisc(String codFisc) {
        this.codFisc = codFisc;
    }

    public int getCodTl() {
        return codTl;
    }

    public void setCodTl(int codTl) {
        this.codTl = codTl;
    }

    public int getCodManag() {
        return codManag;
    }

    public void setCodManag(int codManag) {
        this.codManag = codManag;
    }

    public List<CommessaEntity> getCommesse() {
        return commesse;
    }

    public void setCommesse(List<CommessaEntity> commesse) {
        this.commesse = commesse;
    }

    @Override
    public String toString() {
        return "LavoratoreEntity{" +
                ", idLav=" + idLav +
                ", cognome='" + cognome + '\'' +
                ", nome='" + nome + '\'' +
                ", aziende=" + aziende +
                ", bu=" + bu +
                ", codFisc='" + codFisc + '\'' +
                ", dataAssunzione=" + dataAssunzione +
                ", dataFine=" + dataFine +
                ", codTl=" + codTl +
                ", codManag=" + codManag +
                ", commesse=" + commesse +
                '}';
    }


}
