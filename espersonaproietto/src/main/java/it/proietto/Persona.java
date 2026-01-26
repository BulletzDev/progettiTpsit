package it.proietto;

/**
 * public class Persona<br>
 * extends <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html"> Object</a><br>
 * La classe Persona aiuta la sviluppatore a gestire dati di tipo Persona
 */
public class Persona {
    private String nome, cognome, codice;
    private String regex = "^[A-Z]{6}[0-9]{2}[A-EHLMPRST][0-9]{2}[A-Z][0-9]{3}[A-Z]$";

    /**
     * Costruisce un oggetto dato il nome, il cognome e il codice fiscale (trasformato in automatico in maiuscolo). Se CF non e' valido, viene lanciata un'eccezione.
     *<br><b>CONTROLLO sul CODICE FISCALE:</b>
     <ul>
     *<li>Il codice deve contenere 16 caratteri alfanumerici</li>
     *<li>Il codice non deve contenere spazi</li>
     *<li>I primi 6 caratteri devono essere delle consonanti</li>
     *<li>Settimo e ottavo carattere devono essere numeri</li>
     </ul>
     *Consulta la <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/String.html">Documentazione ufficiale String</a>
     * @param nome - stringa qualsiasi da assegnare come nome di un nuovo oggetto Persona
     * @param cognome - stringa qualsiasi da assegnare come cognome di un nuovo oggetto Persona
     * @param codice - stringa che rappresenta il nuovo codice fiscale dell'oggetto Persona
     * @throws IllegalArgumentException - Se il codice fiscale fornito non e' valido
     */
    public Persona(String nome, String cognome, String codice) {
        this.nome = nome;
        this.cognome = cognome;
        if (codice.length()==16 && !codice.contains(" ") && codice.matches(regex)) {
           this.codice=codice.toUpperCase(); 
        }else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * Il metodo stabilisce se due persone sono omonime; Restituisce un booleano: VERO se le due persone hanno lo stesso nome (senza distinzione di maiuscole e minuscole) altrimenti FALSO
     * @param p - oggetto Persona
     * @return Vero se le due persone confrontate hanno lo stesso nome, altrimenti falso
     */
    public boolean isOmonimo(Persona p){
        if (this.nome.equals(p.getNome()) && this.cognome.equals(p.getCognome())){
            return true;
        }
        return false;
    }

    /**
     * restituisce una Stringa contenente il nome della persona
     * @return il nome di un oggetto Persona
     */
    public String getNome() {
        return nome;
    }

    /**
     * assegna una Stringa come nome di una persona
     * @param nome - Stringa da assegnare come nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * restituisce una Stringa contenente il cognome della persona
     * @return il cognome di un oggetto Persona
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * assegna una Stringa come cognome di una persona
     * @param cognome - nuova stringa da assegnare come cognome a un oggetto Persona già  esistente
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @Override
    public String toString() {
        return "NOME:" + nome + ", COGNOME:" + cognome + ", CODICE:" + codice;
    }

}
