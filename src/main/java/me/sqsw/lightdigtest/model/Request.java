package me.sqsw.lightdigtest.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String title;
    private String text;

    @Enumerated(EnumType.STRING)
    private RequestState state;

    @Embedded
    private PhoneNumber phoneNumber;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "sent_on")
    private LocalDateTime sentOn;
}
