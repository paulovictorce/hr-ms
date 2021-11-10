package com.poliveira.hrpayroll.services;

import com.poliveira.hrpayroll.entities.Payment;
import com.poliveira.hrpayroll.entities.Worker;
import com.poliveira.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

  @Autowired
  private WorkerFeignClient workerFeignClient;

  public Payment getPayment(Long workerId, Integer days) {

    Worker worker = workerFeignClient.findById(workerId).getBody();
    return new Payment(worker.getName(), worker.getDailyIncome(), days);
  }
}
