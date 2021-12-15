package com.bandipo.controllers;


import com.bandipo.model.Notice;
import com.bandipo.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoticeController {



    private final NoticeRepository noticeRepository;

    public NoticeController(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @GetMapping("/notices")
    public List<Notice> getNotices() {
        return noticeRepository.findAllActiveNotices();
    }
//    @GetMapping("/notices")
//    public String getWelcome(){
//
//        return "Welcome to the Spring Security Course";
//    }
}
