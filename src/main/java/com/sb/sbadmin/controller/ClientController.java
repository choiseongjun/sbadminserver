package com.sb.sbadmin.controller;

import com.sb.sbadmin.domain.Client;
import com.sb.sbadmin.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<?> saveClient(@RequestBody Client client) {
        clientService.saveClient(client);
        return ResponseEntity.ok("success");
    }
    @GetMapping
    public ResponseEntity<?> getClient(@RequestParam int pageNum,
                                       @RequestParam int page,
                                       Pageable pageable) {
        pageable = PageRequest.of(page, pageNum, Sort.Direction.DESC, "id");// 내림차순으로 정렬한다
        return ResponseEntity.ok(clientService.getClient(pageable));
    }
}
