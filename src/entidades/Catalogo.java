package entidades;

import java.util.Date;

public class Catalogo {

    private float numeroInterno;
    private String nombreAplicaion;
    private float version;
    private String plataforma;
    private Date fechaInicio;
    private Date fechaTermino;
    private String urlGit;

    public Catalogo() {
    }

    public Catalogo(float numeroInterno, String nombreAplicaion, float version, String plataforma, Date fechaInicio, Date fechaTermino, String urlGit) {
        this.numeroInterno = numeroInterno;
        this.nombreAplicaion = nombreAplicaion;
        this.version = version;
        this.plataforma = plataforma;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.urlGit = urlGit;
    }

    public float getNumeroInterno() {
        return numeroInterno;
    }

    public void setNumeroInterno(float numeroInterno) {
        this.numeroInterno = numeroInterno;
    }

    public String getNombreAplicaion() {
        return nombreAplicaion;
    }

    public void setNombreAplicaion(String nombreAplicaion) {
        this.nombreAplicaion = nombreAplicaion;
    }

    public float getVersion() {
        return version;
    }

    public void setVersion(float version) {
        this.version = version;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public String getUrlGit() {
        return urlGit;
    }

    public void setUrlGit(String urlGit) {
        this.urlGit = urlGit;
    }

    @Override
    public String toString() {
        return "Catalogo{" + "numeroInterno=" + numeroInterno + ", nombreAplicaion=" + nombreAplicaion + ", version=" + version + ", plataforma=" + plataforma + ", fechaInicio=" + fechaInicio + ", fechaTermino=" + fechaTermino + ", urlGit=" + urlGit + '}';
    }

}
