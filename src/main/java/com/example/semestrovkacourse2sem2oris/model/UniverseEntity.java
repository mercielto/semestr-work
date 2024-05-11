//package com.example.semestrovkacourse2sem2oris.model;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.List;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Builder
//@Table(name = "universe")
//public class UniverseEntity {
//
//    @Id
//    @GeneratedValue
//    private Long universeId;
//    private String name;
//
//    @OneToMany(mappedBy = "universe", fetch = FetchType.LAZY)
//    private List<PostEntity> posts;
//}
