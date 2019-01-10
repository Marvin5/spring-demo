package com.example.demo.core.service.impl;

import com.example.demo.core.entity.Phone;
import com.example.demo.core.repository.PhoneRepository;
import com.example.demo.core.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PhoneServiceImpl implements PhoneService {
  @Autowired private PhoneRepository phoneRepository;

  @Override
  @Transactional("jpaTransactionManager")
  public void createPhone(Phone phone) {
    phoneRepository.save(phone);
  }

  public PhoneRepository getPhoneRepository() {
    return phoneRepository;
  }

  public void setPhoneRepository(PhoneRepository phoneRepository) {
    this.phoneRepository = phoneRepository;
  }
}
