package org.api.socialassistancefundapiv1.model;

import javax.persistence.*;

@Entity
@Table(name = "villages")
public class Village {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_location_id", nullable = false, columnDefinition = "INT")
    private SubLocation subLocation;

}
