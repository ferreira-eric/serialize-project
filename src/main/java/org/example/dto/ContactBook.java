package org.example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
public class ContactBook implements Serializable {
    @Serial
    private static final long serialVersionUID = 2118935321917023301L;
    private List<Contact> contactLists = new ArrayList<>();

    public ContactBook(List<Contact> contactLists) {
        this.contactLists = contactLists;
    }

    public ContactBook() {
        this.contactLists = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "ContactBook{" +
                "contactLists=" + contactLists.toString() +
                '}';
    }

}
