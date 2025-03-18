package fr._42.chat.models;
import java.util.List;
import java.util.Objects;

public class Chatroom {
    private int id;
    private String name;
    private User owner;
    private List<Message> messages;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Chatroom chatroom = (Chatroom) o;
        return id == chatroom.id && Objects.equals(name, chatroom.name) && Objects.equals(owner, chatroom.owner) && Objects.equals(messages, chatroom.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner, messages);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}