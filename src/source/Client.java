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

    public void setNumMessages(int numMessages) {
        this.numMessages = numMessages;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void sendMessages() {
        for (int i = 0; i < getNumMessages(); i++) {
            Message newMessage = new Message(getClientID(), this);
            System.out.println("[M " + newMessage.getMessage() + "] created");
            getMessageQueue().enqueue(newMessage);
        }
    }

    public void receiveMessages(Message message) {
        boolean permission;
        while (true) {
            System.out.println("[C " + getClientID() + "] Saving Message");
            permission = getBuffer().saveMessages(message);
            if (permission) {
                // System.out.println("[C " + getClientID() + "] Messages can be added");
                break;
            } else {
                Thread.yield();
            }
        }
        System.out.println("[C " + getClientID() + "] " + message.getMessage() + " received");
        message.sleepMessage();
    }

    public void run() {
        sendMessages();

        for (int i = 0; i < getNumMessages(); i++) {
            receiveMessages(getMessageQueue().dequeue());
        }
        System.err.println("[C " + getClientID() + "] finished.");
    }
}