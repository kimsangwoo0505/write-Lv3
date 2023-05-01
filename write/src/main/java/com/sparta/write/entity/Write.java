package com.sparta.write.entity;


import com.sparta.write.dto.WriteRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Write extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//숫자를 자동으로 더해줌
    private Long id;


    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

//    @Column(nullable = false)
//    private Long userId;
    @OneToMany(mappedBy = "write", cascade = CascadeType.REMOVE)
    List<Letter> letters = new ArrayList<>();
//CascadeType.REMOVE는 JPA에서 엔티티 간의 관계에서 영속성 전이(Persistence Cascade)를 설정하는 옵션 중 하나(?)
//영속성 전이는 한 엔티티의 상태 변화가 관련된 다른 엔티티에도 전파되는 것을 의미(?)
// CascadeType.REMOVE는 Write 엔티티가 삭제될 때 관련된 Letter 엔티티도 함께 삭제되도록 설정하는 역할


    public Write(WriteRequestDto requestDto,String username) {

        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.username = username;

    }

    public void update(WriteRequestDto requestDto) {
        this.title = requestDto.getTitle();
//        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }



}


