package source;

public class Message {

    private int message;
    private Client client;

    public Message(int message, Client client) {
        setMessage(message);
        setClient(client);
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public synchronized void sleepMessage() {
        try {
            System.out.println("[M " + message + "] waiting");
            wait();
        } catch (Exception e) {
            System.err.println("[M] " + (e.getMessage() != null ? e.getMessage() : e) + "from message: " + message);
        }
    }

    public synchronized void wakeMessage() {
        notify();
        System.out.println("[M " + message + "] woke");
    }
}