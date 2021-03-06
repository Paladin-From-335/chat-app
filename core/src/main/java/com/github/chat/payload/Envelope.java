package com.github.chat.payload;

import java.util.Objects;

public class Envelope {

    private String payload;

    private Topic topic;

    public Envelope() {
    }

    public Envelope(String payload, Topic topic) {
        this.payload = payload;
        this.topic = topic;
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
