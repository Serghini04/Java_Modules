package fr._42.chat.models;

import java.util.Objects;

public class Message {
    private int id;
    private User author;
    private String room;
    private String text;
    private String dateTime;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id && Objects.equals(author, message.author) && Objects.equals(room, message.room) && Objects.equals(text, message.text) && Objects.equals(dateTime, message.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, room, text, dateTime);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "Id=" + id +
                ", author='" + author + '\'' +
                ", room='" + room + '\'' +
                ", text='" + text + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}