package it.proietto;

public class Main {
    public static void main(String[] args) {
        Temperatura temp = new Temperatura(25.0);
        System.out.println("Temperatura in Celsius: " + temp.getValoreCelsius());
        System.out.println("Temperatura in Fahrenheit: " + temp.inFahrenheit());
        System.out.println("Temperatura in Kelvin: " + temp.inKelvin());
        temp.aumenta(5.0);
        System.out.println("Dopo aumento, temperatura in Celsius: " + temp.getValoreCelsius());
        temp.diminuisci(10.0);
        System.out.println("Dopo diminuzione, temperatura in Celsius: " + temp.getValoreCelsius());
    }
}