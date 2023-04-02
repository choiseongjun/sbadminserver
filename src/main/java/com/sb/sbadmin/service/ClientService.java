package com.sb.sbadmin.service;

import com.sb.sbadmin.domain.Client;
import com.sb.sbadmin.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public void saveClient(Client client) {
//        SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss", Locale.KOREA );
//        LocalDateTime now = LocalDateTime.now();
//        String nowTime = formatter.format ( now );//세션마지막요청시간
        String nowTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toString();

        client.setApplyTime(nowTime);
        clientRepository.save(client);
    }

    public Page<Client> getClient(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }
}
