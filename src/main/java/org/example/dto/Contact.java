package org.example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@Builder
@NoArgsConstructor
public class Contact implements Serializable {
    @Serial
    private static final long serialVersionUID = -1603549472843075493L;
    private String name;
    private String phoneNumber;
    private String email;
    private String photo;

    public Contact(String name, String phoneNumber, String email, String photo) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
