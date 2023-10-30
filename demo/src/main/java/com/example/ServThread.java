import java.io.*;
import java.net.*;

public class ServerThread extends Thread{
    Socket client;

    BufferedReader inDalCliente;
    DataOutputStream outVersoCliente;

    int numeroRandom = ( int ) ( Math.random() * 100 ) + 1;
    String numeroRicevuto;
    int numeroTentativi;
    int numero;

    public ServerThread(Socket client){
        this.client = client;
    }

    public void run(){
        try{
            inDalCliente = new BufferedReader( new InputStreamReader( client.inDalClient ) );
            outVersoCliente = new DataOutputStream( client.getOutputStream() );

            numeroRicevuto = inDalCliente.readLine();
            numero = Integer.parseInt( numeroRicevuto );

            do{
                if( numero > 100 || numero <= 0){
                    outVersoCliente.writeBytes("Il numero deve essere compreso tra 1 e 100");
                }
                if( numero < numeroRandom ) {
                    outVersoCliente.writeBytes("Il numero è più grande, riprova");
                }
                if ( numero > numeroRandom){
                    outVersoCliente.writeBytes("Il numero è più piccolo, riprova");
                }
            } while( numero != numeroRandom);

            outVersoCliente.writeBytes("Hai indovinato in " + numeroTentativi + " tentativi");
        }
        catch (Exception e){
            System.out.println( e.getMessage );
            System.out.println( "Errore durante l'istanza del server !" );
            System.exit(1);
        }
    }
}