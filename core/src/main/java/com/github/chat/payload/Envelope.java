package com.github.chat.payload;

import com.github.chat.entity.Message;
import com.github.chat.handlers.Topic;

import java.util.Objects;

public class Envelope {

    private String payload;

    private Topic topic;

    private String message;

    public Envelope() {
    }

    public Envelope(String payload, Topic topic) {
        this.payload = payload;
        this.topic = topic;
    }

    public Envelope(String payload, Topic topic, String message) {
        this.payload = payload;
        this.topic = topic;
        this.message = message;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Envelope envelope = (Envelope) o;
        return Objects.equals(payload, envelope.payload) && topic == envelope.topic;
    }

    @Override
    public int hashCode() {
        return Objects.hash(payload, topic);
    }

    @Override
    public String toString() {
        return "Envelope{" +
                "payload='" + payload + '\'' +
                ", topic=" + topic +
                '}';
    }
}
