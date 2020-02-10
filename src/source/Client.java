package source;

public class Client extends Thread {
    private int clientID;
    private Queue<Message> messageQueue;
    private int numMessages;
    private Buffer buffer;

    public Client(int clientID, final int MAX_MESSAGES, Buffer buffer) {
        setClientID(clientID);
        setBuffer(buffer);
        setMessageQueue(new Queue<>());
        setNumMessages(MAX_MESSAGES);
    }

    public Buffer getBuffer() {
        return buffer;
    }

    public void setBuffer(Buffer buffer) {
        this.buffer = buffer;
    }

    public Queue<Message> getMessageQueue() {
        return messageQueue;
    }

    public void setMessageQueue(Queue<Message> messageQueue) {
        this.messageQueue = messageQueue;
    }

    public int getNumMessages() {
        return numMessages;
    }

    public void setNumMessages(int numMensajes) {
        this.numMessages = numMensajes;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void sendMessages() {
        for (int i = 0; i < getNumMessages(); i++) {
            Message newMessage = new Message(clientID + (i + 1), this);
            getMessageQueue().enqueue(newMessage);
        }
    }

    public void receiveMessages(Message message) {
        boolean permission;
        while (true) {
            System.out.println("Saving Message");
            permission = getBuffer().saveMessages(message);
            if (permission) {
                System.out.println("Messages can be added");
                break;
            } else {
                Thread.yield();
            }
        }
        System.out.println("The message " + message.getMensaje() + " was sent to client " + getClientID());
        message.dormir();
    }

    public void run() {
        sendMessages();
        for (int i = 0; i < getNumMessages(); i++) {
            receiveMessages(getMessageQueue().dequeue());
        }
        System.err.print("Client " + getClientID() + " Finished.");
    }
}