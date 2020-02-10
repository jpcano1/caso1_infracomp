package source;

public class Buffer {
    private final Object FULL;
    private int capacity;
    private Queue<Message> messageQueue;
    private int numClients;

    public Buffer(int capacity, int numClients) {
        setCapacity(capacity);
        setMessageQueue(new Queue<>());
        setNumClients(numClients);

        FULL = new Object();
    }

    public boolean isFull() {
        return getMessageQueue().size() == getCapacity();
    }

    public int getNumClients() {
        return numClients;
    }

    public void setNumClients(int numClients) {
        this.numClients = numClients;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Queue<Message> getMessageQueue() {
        return messageQueue;
    }

    public void setMessageQueue(Queue<Message> messageQueue) {
        this.messageQueue = messageQueue;
    }

    public boolean saveMessages(Message message) {
        boolean premission = true;
        synchronized (FULL) {
            if (isFull()) {
                System.out.println("Message queue full");
                premission = false;
            }
        }
        synchronized (this) {
            getMessageQueue().enqueue(message);
            System.out.println("Stored");
        }
        return premission;
    }

    public Message discardMessage() {
        Message currentMessage = null;

        synchronized (this) {
            if (!getMessageQueue().isEmpty()) {
                currentMessage = getMessageQueue().dequeue();
                if (currentMessage.getClient().getMessageQueue().size() == 0) {
                    setNumClients(getNumClients() - 1);
                    System.err.println("Client " + currentMessage.getClient().getId() + " has no more messages");
                    if (getNumClients() <= 0) {
                        System.err.println("No Clients!");
                    }
                }
                System.out.println("Message sent to server.");
            }
        }
        return currentMessage;
    }
}