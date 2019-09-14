package com.example.sbapi.model.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@MappedSuperclass
@Getter
@EntityListeners({AuditingEntityListener.class})
public abstract class CommonProperties {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant modifiedAt;

    /**
     * This allows Spring Data Rest to provide an Etag header that allows us to determine if an entity has been updated
     */
    @Version
    private long version;
}
