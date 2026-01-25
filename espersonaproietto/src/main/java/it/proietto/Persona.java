package it.proietto;

public class Persona {
    private String nome, cognome, codice;
    private String regex = "^[A-Z]{6}[0-9]{2}[A-EHLMPRST][0-9]{2}[A-Z][0-9]{3}[A-Z]$";

    public Persona(String nome, String cognome, String codice) {
        this.nome = nome;
        this.cognome = cognome;
        if (codice.length()>16 && !codice.contains(" ") && codice.matches(regex)) {
           this.codice=codice; 
        }else{
            throw new IllegalArgumentException();
        }
    }

    public boolean isOmonimo(Persona p){
        if (this.nome.equals(p.getNome()) && this.cognome.equals(p.getCognome())){
            return true;
        }
        return false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @Override
    public String toString() {
        return "NOME:" + nome + ", COGNOME:" + cognome + ", CODICE:" + codice;
    }

}
