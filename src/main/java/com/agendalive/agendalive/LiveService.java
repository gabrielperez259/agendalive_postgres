package com.agendalive.agendalive;

import com.agendalive.agendalive.LiveDocument;
import com.agendalive.agendalive.LiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LiveService {

    @Autowired
    LiveRepository liveRepository;

    public Page<LiveDocument> findAll(Pageable pageable, String flag){
        if(flag != null && flag.equals("next")){
            return liveRepository.findByLiveDateAfterOrderByLiveDateAsc(LocalDateTime.now(), pageable);
        }else if(flag != null && flag.equals("previous")){
            return liveRepository.findByLiveDateBeforeOrderByLiveDateDesc(LocalDateTime.now(), pageable);
        }else{
            return liveRepository.findAll(pageable);
        }
    }

    public Optional<LiveDocument> findById(Integer id){
        return liveRepository.findById(id);
    }

    public LiveDocument save(LiveDocument liveDocument){
        return liveRepository.save(liveDocument);
    }

    public void delete(LiveDocument liveDocument){
        liveRepository.delete(liveDocument);
    }
}
