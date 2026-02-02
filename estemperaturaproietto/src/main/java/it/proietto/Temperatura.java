package it.proietto;

/**
 * Rappresenta una temperatura espressa in gradi Celsius. Permette conversioni
 * in Fahrenheit e Kelvin, e modifiche del valore attraverso incrementi o
 * decrementi.
 */
public class Temperatura {

    /**
     * Il valore della temperatura in gradi Celsius.
     */
    protected double valoreCelsius;

    /**
     * Costruisce un oggetto Temperatura a partire da un valore in gradi Celsius.
     * @param valoreCelsius il valore iniziale della temperatura
     */
    public Temperatura(double valoreCelsius) {
        this.valoreCelsius = valoreCelsius;
    }

    /**
     * Aumenta la temperatura di un certo valore.
     * @param delta la quantità di gradi da sommare alla temperatura corrente
     */
    public void aumenta(double delta) {
        this.valoreCelsius += delta;
    }
    /**
     * Diminuisce la temperatura di un certo valore.
     * @param delta la quantità di gradi da sottrarre alla temperatura corrente
     */
    public void diminuisci(double delta) {
        this.valoreCelsius -= delta;
    }

    /**
     * Restituisce il valore attuale della temperatura in gradi Celsius.
     * @return il valore della temperatura in Celsius
     */
    public double getValoreCelsius() {
        return valoreCelsius;
    }

    /**
     * Restituisce la temperatura convertita in gradi Fahrenheit.
     * @return il valore della temperatura in Fahrenheit
     */
    public double inFahrenheit() {
        return (valoreCelsius * 9/5) + 32;
    }

    /**
     * Restituisce la temperatura convertita in Kelvin.
     * @return il valore della temperatura in Kelvin
     */    
    public double inKelvin() {
        return valoreCelsius + 273.15;
    }

    /**
     * Restituisce una rappresentazione testuale della temperatura. <b><i> Esempio</i> </b>: Temperatura: 25.00 °C - 77.00 °F - 298.15 K
     * @return una stringa che rappresenta la temperatura in Celsius, Fahrenheit e Kelvin
     */
    @Override
    public String toString() {
        return "Temperatura :" + getValoreCelsius() + "C - " + inFahrenheit()
                + "F - " + inKelvin() +"K";
    }

    

}
